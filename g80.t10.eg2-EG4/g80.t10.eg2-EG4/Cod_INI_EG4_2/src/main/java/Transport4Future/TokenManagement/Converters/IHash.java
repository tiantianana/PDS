package Transport4Future.TokenManagement.Converters;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public interface IHash {
	public String Hash(String text) throws TokenManagementException;
	

}
