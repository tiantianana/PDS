package Transport4Future.TokenManagement.Data.Operations;

import java.util.HashMap;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Data.Attributes.TokenValue;
import Transport4Future.TokenManagement.Data.Attributes.TypeOfOperation;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.IO.TokenActionParser;
import Transport4Future.TokenManagement.IO.TokenRevocationParser;


public class TokenAction extends TokenChecker implements ITokenOperation {
	private static final String INVALID_OPERATION = "La operación solicitada no puede ser realizada con el token adjuntado a la solicitud.";
	private TokenValue tokenValue;
	private TypeOfOperation typeOfOperation;
	
	public TokenAction(String FileName)  throws TokenManagementException{
		TokenActionParser myParser = new TokenActionParser();
		HashMap<String, String> items = myParser.Parse(FileName);
		this.tokenValue = new TokenValue(items.get(TokenActionParser.TOKEN_VALUE));
		this.typeOfOperation = new TypeOfOperation(items.get(TokenActionParser.TYPE_OF_OPERATION));
		this.executeOperation();
	}
	
	@Override
	public void executeOperation() throws TokenManagementException {
		Token token  = this.verify(this.getTokenValue());
		TokenRequest myRequest = token.checkTokenRequestEmmision();

        if (this.getTypeOfOperation().equals(TypeOfOperation.ACTUATOR)) {
            if(!myRequest.getTypeOfDevice().equals("Actuator")){
                throw new TokenManagementException(INVALID_OPERATION);
            }
        } 
        if (this.getTypeOfOperation().equals(TypeOfOperation.SENSOR)) {
            if(!myRequest.getTypeOfDevice().equals("Sensor")) {
                throw new TokenManagementException(INVALID_OPERATION);
            }
        }
	}
	
	public String getTokenValue() {
		return tokenValue.getValue();
	}

	public void setTokenValue(TokenValue tokenValue) {
		this.tokenValue = tokenValue;
	}

	public String getTypeOfOperation() {
		return typeOfOperation.getValue();
	}

	public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}

	
}
