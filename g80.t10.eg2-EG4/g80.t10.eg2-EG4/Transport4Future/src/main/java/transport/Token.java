//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020

package transport;

import java.util.Date;

class Token {

    private String alg;
    private String typ;
    private String device;
    private Date requestDate;
    private String notificationEmail;
    private long iat;
    private long exp;
    private String Signature;
    private String tokenValue;
	
    public Token (String device, Date requestDate, String notificationEmail) {
    	this.alg = "HS256";
    	this.typ = "PDS";
    	this.device = device;
    	this.requestDate = requestDate;
    	this.notificationEmail = notificationEmail;
    	this.iat = 1583780309;
    	this.exp = this.iat + 604800001;
    	this.Signature = null;
    	this.tokenValue = null;
		
    }
    
    public String getAlg() {
		return alg;
	}
	
	public String gettyp() {
		return typ;
	}
	
	public String getDevice() {
		return device;
	}
	
	public Date getreqDate() {
		return requestDate;
	}
	
	public String getEmail() {
		return notificationEmail;
	}
	
	public long getIat() {
		return iat;
	}
	
	
	public String getSignature() {
		return Signature;
	}
	

	public String getTokenValue() {
		return tokenValue;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getNotificationEmail() {
		return notificationEmail;
	}

	public void setNotificationEmail(String notificationEmail) {
		this.notificationEmail = notificationEmail;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setIat(long iat) {
		this.iat = iat;
	}

	
	public void setSignature(String signature) {
		Signature = signature;
	}


	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	
	public boolean isGranted () {
		if (this.iat < (System.currentTimeMillis() / 1000)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isExpire () {
		System.out.println(this.exp);
		System.out.println(System.currentTimeMillis());
		if (this.exp > (System.currentTimeMillis() / 1000)) {
			return false;
		} else {
			return true;
		}
	}

	public String valuesToSign() {
		return "Token [alg=" + alg + ", typ=" + typ + ", device=" + device + ", iat=" + iat + ", exp=" + exp + "]";
	}

	@Override
	public String toString() {
		return "Token [alg=" + alg + ", typ=" + typ + ", device=" + device + ", requestDate=" + requestDate
				+ ", notificationEmail=" + notificationEmail + ", iat=" + iat + ", exp=" + exp + ", Signature="
				+ Signature + "]";
	}
	
	
	
}
