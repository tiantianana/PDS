package Transport4Future.Utils;

import java.util.Base64;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class Base64Decrypter implements IHash {
	@Override
	public String Hash(String text) throws TokenManagementException {
		byte[] bytes = Base64.getUrlDecoder().decode(text);
	    String decoded = "";
	    for(int i = 0; i < bytes.length; i++) {
	        decoded += (char)bytes[i];
	    }
	    return decoded;
	}
}
