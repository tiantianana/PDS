package Transport4Future.TokenManagement.Converters;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class SHA256Hash extends GenericHasher implements IHash {
	public SHA256Hash() {
		this.algorithm = "SHA-256";
		this.format = "%064x";
	}
	
	@Override
	public String Hash(String text) throws TokenManagementException {
		return super.Hash(text);
	}

}
