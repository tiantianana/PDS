package Transport4Future.TokenManagement.Manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import Transport4Future.TokenManagement.Converters.MD5Hasher;
import Transport4Future.TokenManagement.Converters.SHA256Hash;
import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.Parser.JSONParser;
import Transport4Future.TokenManagement.Parser.JSONTokenParser;
import Transport4Future.TokenManagement.Parser.JSONTokenRequestParser;
import Transport4Future.TokenManagement.Stores.TokenRequestStore;
import Transport4Future.TokenManagement.Stores.TokensStore;

import java.lang.reflect.Type;

public class TokenManager implements ITokenManagement {

	private static TokenManager manager;

	private TokenManager() {

	}

	public static TokenManager getInstance() {
		if (manager == null) {
			manager = new TokenManager();
		} else {
			System.out.println("There is a Token Manager instance already created");
		}
		return manager;
	}

	@Override
	public TokenManager clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException ex) {
			System.out.println("Token Manager Object cannot be cloned");
		}
		return null;
	}

	public String TokenRequestGeneration(String InputFile) throws TokenManagementException {
		TokenRequest myRequest = new TokenRequest(InputFile);
		return myRequest.getHash();
	}

	public String RequestToken(String InputFile) throws TokenManagementException {
		Token myToken = new Token(InputFile);
		return myToken.getTokenValue();
	}

	public boolean VerifyToken(String Token) throws TokenManagementException {
		boolean result = false;
		TokensStore myStore = new TokensStore();

		Token tokenFound = myStore.Find(Token);

		if (tokenFound != null) {
			result = tokenFound.isValid();
		}
		return result;
	}
}