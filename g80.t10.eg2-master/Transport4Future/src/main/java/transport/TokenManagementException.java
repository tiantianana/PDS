//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020

package transport;

public class TokenManagementException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    public TokenManagementException(String message) {

    	this.message = message;
    }

    public String getMessage() {
    	return this.message;
    }
}
