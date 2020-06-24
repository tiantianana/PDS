//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020
package transport;


import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
//import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class TokenStore {
	private List<Token> tokenList;
	
	private void load () {
		try {
			JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "/Store/tokenStore.json"));
			Gson gson = new Gson();
			Token[] myArray = gson.fromJson(reader, Token[].class);
			this.tokenList = new ArrayList<Token>();
			for (Token token: myArray) {
				this.tokenList.add(token);
			}
		} catch (Exception e) {
				this.tokenList = new ArrayList<Token>();
			}
		}
	
	//Añade en el fichero
	public void add (Token newToken) throws TokenManagementException {
		this.load();
		if (find(newToken.getTokenValue()) == null) {
			tokenList.add(newToken);
			this.save();
		}
	}
	
	//Salva y escribe en el fichero
	private void save () throws TokenManagementException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(this.tokenList);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(System.getProperty("user.dir") + "/Store/tokenStore.json");
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save new token in internal license store");
		}
	}
	
	//Encuenta el token
	public Token find (String tokenToFind) {
		Token result = null;
		this.load();
		for (Token token : this.tokenList) {
			//System.out.println(token.getTokenValue());
			if (token.getTokenValue().equals(tokenToFind)) {
				result = token;
			}
		}
		return result;
	}
	
}
