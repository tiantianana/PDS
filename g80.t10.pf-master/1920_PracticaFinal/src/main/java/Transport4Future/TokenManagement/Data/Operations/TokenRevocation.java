package Transport4Future.TokenManagement.Data.Operations;


import java.util.HashMap;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.Attributes.Reason;
import Transport4Future.TokenManagement.Data.Attributes.TokenValue;
import Transport4Future.TokenManagement.Data.Attributes.TypeOfRevocation;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.IO.TokenRevocationParser;


public class TokenRevocation extends TokenChecker implements ITokenOperation {
	private static final String INVALID_REVOCATION = "El token que se quiere revocar ya está revocado en la misma modalidad.";
	private TokenValue tokenValue;
	private TypeOfRevocation typeOfRevocation;
	private Reason reason;
	private String Email;
	
	public TokenRevocation(String FileName) throws TokenManagementException{
		TokenRevocationParser myParser = new TokenRevocationParser();
		HashMap<String, String> items = myParser.Parse(FileName);
		this.tokenValue = new TokenValue(items.get(TokenRevocationParser.TOKEN_VALUE));
		this.typeOfRevocation = new TypeOfRevocation(items.get(TokenRevocationParser.TYPE_OF_REVOCATION));
		this.reason = new Reason(items.get(TokenRevocationParser.REASON));
		this.executeOperation();
	}
	
	@Override
	public void executeOperation() throws TokenManagementException {
		Token myToken = this.verify(this.getTokenValue());
		if(myToken.getRevoked().equalsIgnoreCase(this.getTypeOfRevocation())||((myToken.getRevoked().equalsIgnoreCase("Final")&&this.getTypeOfRevocation().equals("Temporal")))) {
			throw new TokenManagementException(INVALID_REVOCATION);
		}
		myToken.setRevoked(this.getTypeOfRevocation());
		this.Email = myToken.getNotificationEmail();		
	}
	

	public String getTokenValue() {
		return tokenValue.getValue();
	}

	public void setTokenValue(TokenValue tokenValue) {
		this.tokenValue = tokenValue;
	}

	public String getTypeOfRevocation() {
		return typeOfRevocation.getValue();
	}

	public void setTypeOfRevocation(TypeOfRevocation typeOfRevocation) {
		this.typeOfRevocation = typeOfRevocation;
	}

	public String getReason() {
		return reason.getValue();
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String getEmail() {
		return Email;
	}

	

}
