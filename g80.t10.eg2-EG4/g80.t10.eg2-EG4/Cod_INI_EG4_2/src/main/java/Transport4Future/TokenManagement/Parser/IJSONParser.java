package Transport4Future.TokenManagement.Parser;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public interface IJSONParser {
	public JsonObject parseJsonFile(String InputFile) throws TokenManagementException;
}
