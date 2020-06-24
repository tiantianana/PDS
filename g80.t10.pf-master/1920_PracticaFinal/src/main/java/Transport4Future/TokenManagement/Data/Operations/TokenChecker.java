package Transport4Future.TokenManagement.Data.Operations;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TokenChecker {
	public Token verify(String value) throws TokenManagementException {
		Token myToken = new Token ();
		if (myToken.Decode(value)) {
			if(!myToken.isValid()) {
				throw new TokenManagementException("El token que se quiere revocar ya ha caducado.");
			}
		}else {
			throw new TokenManagementException("El token que se quiere revocar no existe.");
		}
		return myToken;
	}
}
