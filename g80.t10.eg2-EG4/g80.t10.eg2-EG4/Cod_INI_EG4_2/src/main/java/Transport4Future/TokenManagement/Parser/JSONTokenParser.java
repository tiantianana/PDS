package Transport4Future.TokenManagement.Parser;

import java.util.HashMap;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class JSONTokenParser extends JSONParser implements IJSONParser {

	
	public HashMap<String, String> ParseJsonFile(String InputFile) throws TokenManagementException{
		JsonObject jsonLicense = super.parseJsonFile(InputFile);
		return this.createToken(jsonLicense);
	}
	
	public HashMap<String, String> createToken(JsonObject jsonLicense) throws TokenManagementException{
		HashMap<String, String> items = new HashMap<String, String>();
		try {			
			items.put("Token Request", jsonLicense.getString("Token Request"));
			items.put("Notification e-mail", jsonLicense.getString("Notification e-mail"));
			items.put("Request Date", jsonLicense.getString("Request Date"));				
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		
		return items;		
	}
	
	
	
	

}
