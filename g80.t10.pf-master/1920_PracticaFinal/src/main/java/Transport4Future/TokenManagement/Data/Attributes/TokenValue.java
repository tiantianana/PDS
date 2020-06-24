package Transport4Future.TokenManagement.Data.Attributes;

import java.util.Base64;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TokenValue extends Attribute {
	public TokenValue (String Value) throws TokenManagementException{
		this.pattern = "([A-Za-z0-9+//=]{232})";
		this.errorMessage = "Error: invalid String length for Token Value.";
		this.value = validate(Value);
	}
	
	public TokenValue (Token token) throws TokenManagementException{
		String stringToEncode = token.getHeader() + token.getPayload() + token.getSignature();
		this.value = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
	}	
}
