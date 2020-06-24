package Transport4Future.TokenManagement.Stores;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TokenRequestStore {
	private static final String STOREPATH = System.getProperty("user.dir") + "/Store/tokenRequestsStore.json";

	public void saveRequest(TokenRequest req, String hex) throws TokenManagementException {
		HashMap<String, TokenRequest> clonedMap = null;
		try {
			clonedMap = this.loadRequestStorage();
			clonedMap.put (hex, req);
		} catch (Exception TokenManagementException){
			clonedMap = new HashMap();
        	clonedMap.put (hex, req);
		}

		
		// Guardar el Tokens Requests Store actualizado
        Gson gson = new Gson();
		String jsonString = gson.toJson(clonedMap);
        FileWriter fileWriter;
        String storePath = STOREPATH;
		try {
			fileWriter = new FileWriter(storePath);
	        fileWriter.write(jsonString);
	        fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}
	}

	public HashMap<String, TokenRequest> loadRequestStorage() throws TokenManagementException {
		//Generar un HashMap para guardar los objetos
		HashMap<String, TokenRequest> clonedMap;
		//Tengo que cargar el almacen de tokens request en memoria y añadir el nuevo si no existe
		try {
			Gson gson = new Gson();
			String jsonString;
			String storePath = STOREPATH;
			
			Object object = gson.fromJson(new FileReader(storePath), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			
			throw new TokenManagementException("Error: unable to recover Token Requests Store.");
			
		}
		return clonedMap;
	}

}
