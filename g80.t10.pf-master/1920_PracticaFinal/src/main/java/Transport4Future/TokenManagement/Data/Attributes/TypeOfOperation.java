package Transport4Future.TokenManagement.Data.Attributes;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TypeOfOperation extends Attribute {
	public static final String SENSOR = "Send Information from Sensor";
	public static final String ACTUATOR = "Send Request to Actuator";

	public TypeOfOperation (String Value) throws TokenManagementException {
		this.errorMessage = "Error: invalid type of operation.";
		this.value = this.validate(Value);
	}
	
	protected String validate (String Value) throws TokenManagementException{
		if (!(Value.equalsIgnoreCase(SENSOR) || Value.equalsIgnoreCase(ACTUATOR)|| Value.equalsIgnoreCase("Check State"))) {
			throw new TokenManagementException(this.errorMessage);	
		} 
		return Value;
	}
}
