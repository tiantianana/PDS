package Transport4Future.TokenManagement.Data.Operations;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public interface ITokenOperation {
	void executeOperation () throws TokenManagementException;
	
}
