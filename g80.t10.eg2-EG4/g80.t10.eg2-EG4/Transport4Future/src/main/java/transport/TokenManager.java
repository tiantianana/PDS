//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020

package transport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;


public class TokenManager {
	
	/****************************Parte 1*****************************************+*/ 
	
	public String tokenRequestGeneration(String path) throws TokenManagementException {
		TokenRequest token;
		String myToken;
		
		token = readTokenRequestFromJSON(path);
		checkInitialTokenInformationFormat(token);
		myToken = codeHashMD5(token);
		//return Token.toString only for testing
		return myToken;
	}

	public TokenRequest readTokenRequestFromJSON(String path) throws TokenManagementException {
	TokenRequest req = null;
 	

	String fileContents = "";

	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader(path));
	} catch (FileNotFoundException e) {
	    throw new TokenManagementException("Error: file not found");
	}
	String line;
	try {
	    while ((line = reader.readLine()) != null) {
	    	fileContents += line;
	    }
        } catch (IOException e) {
            throw new TokenManagementException("Error: input file could not be accessed.");
        }
	try {
	    reader.close();
	} catch (IOException e) {
	    throw new TokenManagementException("Error: input file could not be accessed.");
	}
	
	JsonObject jsonLicense = null;
	try {
		jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
	} catch (JsonParsingException e) {
		throw new TokenManagementException("Error: Json can not be created due to incorrect representation");
	}
	
	try {
	    String deviceName = jsonLicense.getString("Device Name");
	    String typeOfDevice = jsonLicense.getString("Type of Device");
	    String driverVersion = jsonLicense.getString("Driver Version");
	    String supportEmail = jsonLicense.getString("Support e-mail");
	    String serialNumber = jsonLicense.getString("Serial Number");
	    String macAddress = jsonLicense.getString("MAC Address");
			
	    req = new TokenRequest(deviceName, typeOfDevice, driverVersion, supportEmail, serialNumber, macAddress);
	    
	} catch (Exception pe) {
	    throw new TokenManagementException("Error: Invalid input data in JSON structure");
	}
	
	return req;
    }
	
	// Comprobar parametros
	private void checkInitialTokenInformationFormat(TokenRequest request) throws TokenManagementException {
		
		//Check Length for device name
		if (request.getDeviceName().length() < 1 || request.getDeviceName().length() > 20) {
			throw new TokenManagementException("Error: Invalid String lenght for device name");
		}
		
		//Check Length for type of device
		if (request.getTypeOfDevice().length() < 1 || (!request.getTypeOfDevice().equalsIgnoreCase("Actuator")) && (!request.getTypeOfDevice().equalsIgnoreCase("Sensor"))) {
			throw new TokenManagementException("Error: Invalid Type of Device" );
		}

		//Check Length for driver version
		if (request.getDriverVersion().length() < 1 || request.getDriverVersion().length() > 25 || !request.getDriverVersion().matches("[0-9]{4}+.[0-9]{2}+.[0-9]{2}$")) {
			throw new TokenManagementException("Error: Invalid String driver version");
		}
		
		//Check Valid Mail
		if (!request.getSupportEmail().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			throw new TokenManagementException("Error: Invalid Email format");
		}
		
		//Check Serial Number: Combination of letters, numbers and bars without spaces
		if (!request.getSerialNumber().matches("[0-9]{10}$")) {
			throw new TokenManagementException("Error: Invalid serial number");
		}
		
		//Invalid Mac Adress
		if (!request.getMacAdress().matches("^^([0-9A-Fa-f]{2}[:-]){4}([0-9A-Fa-f]{2})$")) {
			throw new TokenManagementException("Error: Invalid MAC Address");
		}
	}
	
    public String codeHashMD5(TokenRequest mytoken) throws TokenManagementException {
	MessageDigest md;
	try {		
            md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    throw new TokenManagementException("Error: no such hashing algorithm");
	}
	String input = "Stardyst" + "-" + mytoken.toString();
	
	md.update(input.getBytes(StandardCharsets.UTF_8));
	byte[] digest = md.digest();
	
	String hex = String.format("%32x", new BigInteger(1, digest));
	
	return hex;
    }
	
	
    public String codeHash256(Token token) throws TokenManagementException {
    	MessageDigest md;
    	try {		
    		md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm");
		}
		String input = "Stardust" + "-" + token.valuesToSign();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		
		String hex = String.format("%64x", new BigInteger(1, digest));
		
		return hex;
			
		}
	
	private String encodeString(String stringToEncode) throws TokenManagementException {
		String encodedURL;
		try {
			encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		} catch (Exception ex) {
			throw new TokenManagementException("Error encoding 64URL.");
		} 
		return encodedURL;
	}
	
	
	/****************************Parte 2*****************************************+*/ 
	
	public String requestToken(String inputFile) throws TokenManagementException {
		Token token;
		String myToken;
		
		token = readRequestTokenFromJson(inputFile);
		
		myToken = codeHash256(token);
		token.setSignature(myToken);
		myToken = encodeString(myToken);
		token.setTokenValue(myToken);
		
		TokenStore myStore = new TokenStore();
		myStore.add(token);
		String signature = token.getSignature();
		return encodeString(signature);
	}

	public Token readRequestTokenFromJson(String path) throws TokenManagementException {
		Token myToken = null;
		String fileContents = "";
				
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			throw new TokenManagementException("Error: file not found");
		}
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			} 
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be accessed");
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be closed");
		}
		
		JsonObject jsonLicense = null;
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
		} catch (JsonParsingException e) {
			throw new TokenManagementException("Error: Json can not be created due to incorrect representation");
		}
		
		String tokenRequest;
		String email;
		String requestDate;
		
		try {
			tokenRequest = jsonLicense.getString("Token Request");
			email = jsonLicense.getString("Notification e-mail");
			requestDate = jsonLicense.getString("Request date");
			
		} catch (Exception e) {
			throw new TokenManagementException("Error: Invalid input data in JSON structure");
		}
		
		//Check date
		String dateString;
		Date date;
		try {
			SimpleDateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    date       = dateFormat.parse(requestDate); 
		    dateString = dateFormat.format(date);
		} catch (Exception Ex) {
			throw new TokenManagementException("Error: Invalid Date Format");
		}
		
		//Check date, mas restrinctivo, ya que el dateformat no detecta errores como el doble espacio etc
		if (!requestDate.matches("^(([0-2]\\d|[3][0-1])\\/([0]\\d|[1][0-2])\\/[2][0]\\d{2}\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$")) {
			throw new TokenManagementException("Error: Invalid Date Format due to incorrect sintaxis");
		}
		
		//Check valid token
		if (tokenRequest.length() < 1) {
			throw new TokenManagementException("Error: Invalid Token Format");
		}
		//Check Valid Mail
		if (!email.matches ("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			throw new TokenManagementException("Error: Invalid Email Format");
		}	
		
		myToken = new Token(tokenRequest, date, email);
		
		return myToken;
	
	}

	
		/****************************Parte 3*****************************************+*/ 
	public boolean verifyToken(String token) throws TokenManagementException {
		TokenStore myStore = new TokenStore ();
		boolean result = false;
		
		Token tokenFound = myStore.find(token);
		if (tokenFound != null) {
			result = isValid(tokenFound);
		}
		
		if (!result) {
			throw new TokenManagementException("Error: Token is not found in TokenStore");
		}
		return result;
	}
	
	private boolean isValid (Token tokenFound) {
		if ((!tokenFound.isExpire()) && (tokenFound.isGranted())) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
}
