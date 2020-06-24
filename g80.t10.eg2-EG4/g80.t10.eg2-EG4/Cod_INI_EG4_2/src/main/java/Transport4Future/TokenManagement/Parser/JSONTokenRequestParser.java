package Transport4Future.TokenManagement.Parser;

import java.util.HashMap;

import javax.json.JsonObject;
import javax.json.stream.JsonParser;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class JSONTokenRequestParser extends JSONParser implements IJSONParser {
	public HashMap<String, String> ParseJsonFile(String InputFile) throws TokenManagementException{
		JsonObject jsonLicense = super.parseJsonFile(InputFile);
		return this.createTokenRequest(jsonLicense);
	}
	
	public HashMap<String, String> createTokenRequest(JsonObject jsonLicense) throws TokenManagementException{
		HashMap<String, String> items = new HashMap<String, String>();
		try {	
			items.put("Device Name", jsonLicense.getString("Device Name"));
			items.put("Type of Device", jsonLicense.getString("Type of Device"));
			items.put("Driver Version", jsonLicense.getString("Driver Version"));
			items.put("Support e-mail", jsonLicense.getString("Support e-mail"));
			items.put("Serial Number", jsonLicense.getString("Serial Number"));
			items.put("MAC Address", jsonLicense.getString("MAC Address"));			
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		
		return items;
	}
}
