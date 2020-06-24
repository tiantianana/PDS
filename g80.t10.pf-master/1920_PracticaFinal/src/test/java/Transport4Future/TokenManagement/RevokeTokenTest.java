package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.Store.TokensRequestStore;
import Transport4Future.TokenManagement.Store.TokensStore;

class RevokeTokenTest {

    private TokenManager myManager;

    public RevokeTokenTest () {
        myManager = TokenManager.getInstance();
    }
    
    private void insertFirstRequest () throws TokenManagementException {
		TokensRequestStore myStore = TokensRequestStore.getInstance();
		myStore.Reset();
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/CorrectFileSensor.json";
		myManager.TokenRequestGeneration(InputFile);
	}
	
	private String insertFirstToken () throws TokenManagementException {
		TokensStore myStore = TokensStore.getInstance();
		myStore.Reset();
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest.json";
		return myManager.RequestToken(InputFile);
	}
	
	
	@Test
	@DisplayName("Token Caducado")
	void IntentoTokenCaducado() throws TokenManagementException {
		this.insertFirstRequest();
		String TokenValue = this.insertFirstToken();
		Token myToken = new Token();
		myToken.Decode(TokenValue);
		myToken.CaducarToken();
        TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
            myManager.RevokeToken(System.getProperty("user.dir") +"./TestData/RevokeTokenTest/PE-RF4-NV-61.json");
        });
        assertEquals ("El token que se quiere revocar ya ha caducado.",ex.getMessage());
	}
	

    @DisplayName("Valid Test Cases")
    @ParameterizedTest(name = "{index} - {2} ")
    @CsvFileSource(resources = "/validTestCasesRevokeTokenTest.csv")
    void ValidTestCases(String InputFilePath, String Result) throws TokenManagementException {
    	this.insertFirstRequest();
    	this.insertFirstToken();
        String myResult = myManager.RevokeToken(InputFilePath);
        assertEquals (Result,myResult);
    }
    
  
    @DisplayName("Valid Test Cases Estructural")
    @ParameterizedTest(name = "{index} - {2} ")
    @CsvFileSource(resources = "/validTestCasesRevokeTokenTestEstructural.csv")
    void ValidTestCases(String InputFilePath1, String InputFilePath2, String Result) throws TokenManagementException {
    	this.insertFirstRequest();
    	this.insertFirstToken();
    	myManager.RevokeToken(InputFilePath1);
        String myResult = myManager.RevokeToken(InputFilePath2);
        assertEquals (Result,myResult);
    }

 
    @DisplayName ("Invalid Test Cases")
    @ParameterizedTest(name = "{index} - {2}") 
    @CsvFileSource(resources = "/invalidTestCasesRevokeTokenTest.csv")
    void InvalidTestCases(String InputFilePath, String expectedMessage) {
        TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        	myManager.RevokeToken(InputFilePath);
        });
        assertEquals (expectedMessage,ex.getMessage());
    }
    
   
    @DisplayName("Invalid Test Cases Estructural")
    @ParameterizedTest(name = "{index} - {2} ")
    @CsvFileSource(resources = "/invalidTestCasesRevokeTokenTestEstructural.csv")
    void invalidTestCases(String InputFilePath1, String InputFilePath2, String expectedMessage) throws TokenManagementException {
    	this.insertFirstRequest();
    	this.insertFirstToken();
    	myManager.RevokeToken(InputFilePath1);
    	TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        	myManager.RevokeToken(InputFilePath2);
        });
        assertEquals (expectedMessage, ex.getMessage());
    }
  

}
