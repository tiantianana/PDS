//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020

package transport;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerifyTokenTest {
	
	private TokenManager myManager;
	
	public VerifyTokenTest () {
		myManager = new TokenManager();
	}
	
	//Prueba 1. Token que se encuentre en fichero y no ha expirado y fecha de emisión mayor a la actual
	@DisplayName("Prueba 1. Token correct")
	@Test
	void correctVerifyTokenTest() throws TokenManagementException {	
		boolean expected = true;
		String tokenToVerify = "NWExNTQ1YmEwYWEzM2I2NWM3ZWM4NThiOTQ0Y2Q2ZDkyM2FiYzNmMGY0MTg5MWY2OGRmYmY4YzQ5ZWJmOTE0MQ==";
		boolean obtained = myManager.verifyToken(tokenToVerify);
		assertEquals(expected, obtained);
	}
	
	//Prueba 2 : Token que se encuentre en fichero y no ha expirado y fecha de emisión menor a la actual
	
		
	//Prueba 3. Token que se encuentre en fichero y ha expirado y fecha de emisión mayor a la actual
	@DisplayName("Prueba 3")
	@Test
	void tokenExpireTest() throws TokenManagementException {
		
		String tokenToVerify = "5136a7fc64013259b958ebc707530c7e";
		String expectedMessage = "Token has expired";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.verifyToken(tokenToVerify);
		});
		assertEquals(expectedMessage, ex.getMessage());
	}
	
	//Prueba 4 : Token que se encuentre en fichero y ha expirado y fecha de emisión menor a la actual
	
	//Prueba 5. Token que no se encuentre en fichero
		@DisplayName("Prueba 5")
		@Test
		void tokenWrongVerifyTest() throws TokenManagementException {
			
			String tokenToVerify = "abc";
			String expectedMessage = "Error: Token is not found in TokenStore";
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.verifyToken(tokenToVerify);
			});
			assertEquals(expectedMessage, ex.getMessage());
		}

	
		
	//Prueba 6
		@DisplayName("Prueba 6. Iteracion 1 del for")
		@Test
		void iteracion1Test() throws TokenManagementException {	
			boolean expected = true;
			String tokenToVerify = "ZjhmZDI5ZGFhOWM4NGI0ZjI0Y2YwMjI1NTkyYzY5NTcxOTQ5MmI4YzliZjRkY2U3MDRlNWMwNzgyN2NlMGMwNA==";
			boolean obtained = myManager.verifyToken(tokenToVerify);
			assertEquals(expected, obtained);
		}
		
	//Prueba 7
		@DisplayName("Prueba 7. Iteracion 2 del for")
		@Test
		void iteracion2Test() throws TokenManagementException {	
			boolean expected = true;
			String tokenToVerify = "IDkwN2I5OWY3YzM5NDg5MzlmYTJlZmE2ZjlhMTc3MWU3MzYwNTE5OTlkMGRiZTk0YzAyNDFlMDAxYWQyOGNmZA==";
			boolean obtained = myManager.verifyToken(tokenToVerify);
			assertEquals(expected, obtained);
		}
		
	//Prueba 8. Fichero vacio
		
}
