package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.Store.TokensRequestStore;
import Transport4Future.TokenManagement.Store.TokensStore;

class ExecuteActionTest {

    private TokenManager myManager;

    public ExecuteActionTest () {
        myManager = TokenManager.getInstance();

    }
	
	private void insertFirstRequest (String Operation) throws TokenManagementException {
		TokensRequestStore myStore = TokensRequestStore.getInstance();
		myStore.Reset();
		String InputFile;
		if(Operation.equals("Sensor")) {
			InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/CorrectFileSensor.json";
		}else {
			InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/CorrectFileActuator.json";
		}
		myManager.TokenRequestGeneration(InputFile);
	}
	
	private String insertFirstToken (String Operation) throws TokenManagementException {
		TokensStore myStore = TokensStore.getInstance();
		myStore.Reset();
		String InputFile;
		if(Operation.equals("Sensor")) {
			InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest.json";
		}else {
			InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequestActuator.json";
		}
		return myManager.RequestToken(InputFile);
	}
	

	@Test
	@DisplayName("Token Caducado")
	void IntentoTokenCaducado() throws TokenManagementException {
		this.insertFirstRequest("Sensor");
		String TokenValue = this.insertFirstToken("Sensor");
		Token myToken = new Token();
		myToken.Decode(TokenValue);
		myToken.CaducarToken();
        TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
            myManager.ExecuteAction(System.getProperty("user.dir") +"./TestData/ExecuteActionTest/PE-RF5-NV-48.json");
        });
        assertEquals ("El token que se quiere revocar ya ha caducado.",ex.getMessage());
	}
    

    @DisplayName("Valid Test Cases")
    @ParameterizedTest(name = "{index} - {2} ")
    @CsvFileSource(resources = "/validTestCasesExecuteActionTest.csv")
    void ValidTestCases(String InputFilePath, String Result) throws TokenManagementException {
    	this.insertFirstRequest("Sensor");
    	this.insertFirstToken("Sensor");
        String myResult = Boolean.toString(myManager.ExecuteAction(InputFilePath));
        assertEquals (Result,myResult);
    }
    

    @DisplayName("Valid Test Cases Estructural")
    @ParameterizedTest(name = "{index} - {2} ")
    @CsvFileSource(resources = "/validTestCasesExecuteActionTestEstructural.csv")
    void ValidTestCases(String InputFilePath, String Device, String Result) throws TokenManagementException {
    	this.insertFirstRequest(Device);
    	this.insertFirstToken(Device);
        String myResult = Boolean.toString(myManager.ExecuteAction(InputFilePath));
        assertEquals (Result,myResult);
    }

 
    @DisplayName ("Invalid Test Cases")
    @ParameterizedTest(name = "{index} - {2}") 
    @CsvFileSource(resources = "/invalidTestCasesExecuteActionTest.csv")
    void InvalidTestCases(String InputFilePath, String expectedMessage) {
        TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
            myManager.ExecuteAction(InputFilePath);
        });
        assertEquals (expectedMessage,ex.getMessage());
    }
    

    @DisplayName ("Invalid Test Cases Estructural")
    @ParameterizedTest(name = "{index} - {2}") 
    @CsvFileSource(resources = "/invalidTestCasesExecuteActionTestEstructural.csv")
    void InvalidTestCases(String InputFilePath, String Device, String expectedMessage) throws TokenManagementException{
    	this.insertFirstRequest(Device);
    	this.insertFirstToken(Device);
        TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
            myManager.ExecuteAction(InputFilePath);
        });
        assertEquals (expectedMessage,ex.getMessage());
    }
 

}
