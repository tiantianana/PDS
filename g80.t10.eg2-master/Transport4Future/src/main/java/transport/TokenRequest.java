//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020


package transport;

import javax.json.JsonObject;

public class TokenRequest {
	
    private String deviceName;
    private String typeOfDevice;
    private String driverVersion;
    private String supportEmail;
    private String serialNumber;
    private String macAddress;
	
    public TokenRequest(String deviceName, String typeOfDevice, String driverVersion, String supportEmail, String serialNumber, String macAddress) {
	this.deviceName = deviceName;
	this.typeOfDevice = typeOfDevice;
	this.driverVersion = driverVersion;
	this.supportEmail = supportEmail;
	this.serialNumber = serialNumber;
	this.macAddress = macAddress;
    }
	
    @Override
    public String toString() {
	return "TokenRequest [\\n\\Device Name=" + this.deviceName + ",\n\t\\Serial Type Of Device=" 
				+ this.typeOfDevice + ",\n\t\\Driver Version="
				+ this.driverVersion + ",\n\t\\Support Email="
				+ this.supportEmail + ",\n\t\\Serial Number="
				+ this.serialNumber + ",\n\t\\MAC Address=" + this.macAddress + "\n]";
    }

	public String getDeviceName() {
		return deviceName;
	}
	
	public String getTypeOfDevice() {
		return typeOfDevice;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public String getSupportEmail() {
		return supportEmail;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public String getMacAdress() {
		return macAddress;
	}

	public long getSize(JsonObject jsonLicense) {
		return jsonLicense.size();
	}
	
	
	
}
