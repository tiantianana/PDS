//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020


package transport;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class requestTokenTest {
	
	private TokenManager myManager;
	private String jsonFilesFolder;
	
	public requestTokenTest() {
		jsonFilesFolder = System.getProperty("user.dir") + "/JsonFile/RequestToken/";
		
		myManager = new TokenManager();
	}

	/*
	Fichero existe
	Se cubren todos los nodos terminales con casos validos
	Nodos cubiertos: 5,11,15,19,26,33,40,44-53,60-63,75-91
	*/
	@DisplayName("Correct Token Generation")
	@Test
	void correctTokenGenerationTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_V_01.json";
		String expectedToken = "ZjhmZDI5ZGFhOWM4NGI0ZjI0Y2YwMjI1NTkyYzY5NTcxOTQ5MmI4YzliZjRkY2U3MDRlNWMwNzgyN2NlMGMwNA==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}
	
	@DisplayName("File is missing")
	@Test
	void filesIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "jfksaldkCorrect.json";
		String expectedMessage = "Error: file not found";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 2 eliminado
	Nodos cubiertos: 2,5,4,11
	*/
	@DisplayName("Nodo 2 is missing ({{)")
	@Test
	void bracketMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_02.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 2 duplicado
	Nodos cubiertos: 2,5,4,11
	*/
	@DisplayName("Nodo 2 is duplicate ({{)")
	@Test
	void duplicateBracketTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_03.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	/*
	Nodo no terminal 3 eliminado 
	Nodos cubiertos: 
	*/
	@DisplayName("Nodo no terminal 3 eliminado ")
	@Test
	void nothingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_04.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	//Json lee unicamente la segunda variable, por lo que no da problema.
	@DisplayName("Duplicate all Variables")
	@Test
	void duplicateAllVariablesTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_05.json";
		String expectedToken = "ZjhmZDI5ZGFhOWM4NGI0ZjI0Y2YwMjI1NTkyYzY5NTcxOTQ5MmI4YzliZjRkY2U3MDRlNWMwNzgyN2NlMGMwNA==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
		
	}
	
	/***********************************Token Request********************************************************/
	
	/*
	Nodo no terminal 6 eliminado 
	Nodos cubiertos: 6,8,10
	*/
	@DisplayName("Token Request Name missing")
	@Test
	void missingTokenRequestTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_06.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 6 duplicado
	Nodos cubiertos: 6,8,10
	*/
	//No se produce error ya que json lee unicamente el segundo valor.
	@DisplayName("Token Request Name duplicate")
	@Test
	void duplicateTokenRequestTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_07.json";
		String expectedToken = "ZjhmZDI5ZGFhOWM4NGI0ZjI0Y2YwMjI1NTkyYzY5NTcxOTQ5MmI4YzliZjRkY2U3MDRlNWMwNzgyN2NlMGMwNA==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
		
	}
	
	
	/***********************************Date*****************************************************/
	
	/*
	Nodo no terminal 65 eliminado 
	Nodos cubiertos: 65, 82, 68, 84
	*/
	@DisplayName("Date Format wrong Missing /")
	@Test
	void wrongDateTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_08.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 65 duplicado
	Nodos cubiertos: 65, 82, 67, 84
	*/
	@DisplayName("Double Line Date Format wrong")
	@Test
	void wrongDate2Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_09.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 71 eliminado 
	Nodos cubiertos: 71, 88, 73, 90
	*/
	@DisplayName("Date Format wrong Missing :")
	@Test
	void wrongDate3Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_10.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}

	/*
	Nodo no terminal duplicado
	Nodos cubiertos: 71, 88, 73, 90
	*/
	@DisplayName("Duplicate : Date Format wrong")
	@Test
	void wrongDate4Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_11.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/******************************Token Request***************************************/
	/*
	Nodo no terminal 12 eliminado 
	Nodos cubiertos: 12, 16, 20
	*/
	@DisplayName("Missing Token Request Name")
	@Test
	void missingTokenRequest2Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_12.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	
	/*
	Nodo no terminal 12 duplicado
	Nodos cubiertos: 12, 16, 20
	*/
	@DisplayName("*****Duplicado Token Request Name")
	@Test
	void tokenRequestNameDuplicateTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_13.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 12 duplicado
	Nodos cubiertos: 12, 16, 20
	*/
	@DisplayName("Wrong Format Token Request Name")
	@Test
	void tokenRequestNameWrongFormateTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_14.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 13 duplicado
	Nodos cubiertos: 13, 26, 17, 33, 21, 40
	*/

	@DisplayName("Wrong Format Token Request Name")
	@Test
	void tokenRequestNameWrongFormate2Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_15.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 14 eliminado 
	Nodos cubiertos: 14, 18, 22
	*/
	@DisplayName("Missing Token Request Name")
	@Test
	void missingTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_16.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 14 duplicado
	Nodos cubiertos: 14, 18, 22
	*/
	//En este caso auqnue no se produce un error en la estructura de json, nos lo marca como tal ya que no sigue el formato adecuado de los valores de entrada.
	@DisplayName("Token Request Value Duplicate")
	@Test
	void duplicateTokenRequestStringTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_17.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	
	/*
	Nodo no terminal 23 eliminado 
	Nodos cubiertos: 23,44,25,46,27,47,29,49,30,50,32,52,34,53,36,59,37,60,39,62,41,63,43,75
	*/
	@DisplayName("Token Request Name Quotation Mark")
	@Test
	void quoteTokenRequestStringTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_18.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 23 duplicado
	Nodos cubiertos: 23,44,25,46,27,47,29,49,30,50,32,52,34,53,36,59,37,60,39,62,41,63,43,75
	*/
	@DisplayName("Token Request Name Duplicate Quotation Mark")
	@Test
	void duplicateQuoteTokenRequestStringTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_19.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 24 eliminado 
	Nodos cubiertos: 24, 45, 31, 51, 38, 61
	*/
	@DisplayName("Token Request Name Missing")
	@Test
	void missingTokenRequestName2Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_20.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 24 duplicado
	Nodos cubiertos: 24, 45, 31, 51, 38, 61
	*/
	@DisplayName("Token Request Name Double")
	@Test
	void doubleTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_21.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 28 eliminado 
	Nodos cubiertos: 28,48, 35, 42
	*/
	@DisplayName("Token Request Name None")
	@Test
	void noneTokenRequestTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_22.json";
		String expectedMessage = "Error: Invalid Token Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	
	/*
	Nodo no terminal 28 eliminado 
	Nodos cubiertos: 28,48, 35, 42
	*/
	
	@DisplayName("*************Token Request Name Double")
	@Test
	void doubleTokenRequest2Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_23.json";
		String expectedToken = "IDkwN2I5OWY3YzM5NDg5MzlmYTJlZmE2ZjlhMTc3MWU3MzYwNTE5OTlkMGRiZTk0YzAyNDFlMDAxYWQyOGNmZA==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
		
	}
	
	/*
	Nodo no terminal 7 eliminado 
	Nodos cubiertos: 7,15,9,19
	*/
	@DisplayName("Token Request Name Missing Comma")
	@Test
	void missingCommaTokenRequestTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_24.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 7 duplicado
	Nodos cubiertos: 7,15,9,19
	*/
	@DisplayName("Token Request Double Comma")
	@Test
	void twoCommaTokenRequestTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_25.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/********************************Email*****************************************************/
	/*
	Nodo no terminal 54 eliminado 
	Nodos cubiertos: 54, 76, 55, 77, 56, 78, 57, 79, 58, 80
	*/
	@DisplayName("Missing First Part of the Email")
	@Test
	void missingFirstTimeEmailTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_26.json";
		String expectedMessage = "Error: Invalid Email Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 54 eliminado 
	Nodos cubiertos: 54, 76, 55, 77, 56, 78, 57, 79, 58, 80
	*/
	@DisplayName("Double First Part of the Email")
	@Test
	void doubleFirstTimeEmailTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_27.json";
		String expectedToken = "ZjhmZDI5ZGFhOWM4NGI0ZjI0Y2YwMjI1NTkyYzY5NTcxOTQ5MmI4YzliZjRkY2U3MDRlNWMwNzgyN2NlMGMwNA==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}
	
	/*******************************************Date**************************************************/
	/*
	Nodo no terminal 64 eliminado 
	Nodos cubiertos: 64, 81, 66, 83, 68, 85, 70, 87, 72, 89, 74, 91
	*/
	@DisplayName("Missing First Part of the Date")
	@Test
	void missingFirstTimeDateTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_28.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 64 duplicado
	Nodos cubiertos: 64, 81, 66, 83, 68, 85, 70, 87, 72, 89, 74, 91
	*/
	//El parse de date nos detecta como fecha correcta ya que no detecta errores de bajo nivel, por lo que damos el test como valido.
	@DisplayName("Double First Part of the Date")
	@Test
	void doubleFirstTimeDateTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_29.json";
		String expectedMessage = "Error: Invalid Date Format due to incorrect sintaxis";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 44 modificado
	Nodos cubiertos: 44, 46, 47, 49, 50, 52, 53, 59, 60, 62, 63, 75  
	*/
	@DisplayName("Wrong First Part of the Token Request Name")
	@Test
	void wrongFirstPartofTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_30.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 45 modificado
	Nodos cubiertos: 45
	*/
	@DisplayName("Modificate Token Request Name")
	@Test
	void modificateTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_31.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 26 modificado
	Nodos cubiertos: 26, 33, 40
	*/
	@DisplayName("Modificate Second Part Token Request Name")
	@Test
	void modificateSecondPartTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_32.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 48 modificado
	Nodos cubiertos: 48
	*/
	//Consideramos que token puede leer cualquier tipo de caracter
	@DisplayName("Modificate String Part Token Request Name")
	@Test
	void modificateStringPartTokenRequestNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_33.json";
		String expectedToken = "MzRiMWIwNjFhZDc0Y2YyMzY1OGU4ZDM0MTIyNWE4YTBiZDUwYjRhYjJmNmZhM2FlYzNmZGZmODQ0MjkwODM2Mg==";
		String obtainedToken = myManager.requestToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}
	
	/*********************************Email********************************************/
	/*
	Nodo terminal 51 modificado
	Nodos cubiertos: 51
	*/
	@DisplayName("Modificate Email Name")
	@Test
	void modificateEmailNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_34.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 76 modificado
	Nodos cubiertos: 76,77,78,79,80
	*/
	@DisplayName("Modificate Second Part Email Name")
	@Test
	void modificateSecondPartEmailNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_35.json";
		String expectedMessage = "Error: Invalid Email Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/******************************************Date*********************************************/
	/*
	Nodo terminal 61 modificado
	Nodos cubiertos: 61
	*/
	@DisplayName("Modificate Date Name")
	@Test
	void modificateDatelNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_36.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 81 modificado
	Nodos cubiertos: 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91 
	*/
	@DisplayName("Added Extra Line Date Name")
	@Test
	void addedExtraLinelDateNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_37.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo terminal 2 modificado
	Nodos cubiertos: 2, 11
	*/
	@DisplayName("Modificate First Bracket")
	@Test
	void modificateFirstBracketTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_38.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/******************************Date****************************************/
	/*
	Nodo no terminal 69 eliminado 
	Nodos cubiertos: 69, 86
	*/
	//El parse date no detecta si hay más espacios de lo debido, por lo que comprobamos con el regex
	@DisplayName("No Space Date Format")
	@Test
	void noSpaceDateFormatTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_39.json";
		String expectedMessage = "Error: Invalid Date Format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	/*
	Nodo no terminal 69 duplicado
	Nodos cubiertos: 69, 86
	*/
	//El parse date no detecta si hay más espacios de lo debido, por lo que comprobamos con el regex
	@DisplayName("Too Much Space Date Format")
	@Test
	void tooMuchSpaceDateFormatTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "AS_RF2_NV_40.json";
		String expectedMessage = "Error: Invalid Date Format due to incorrect sintaxis";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.requestToken(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	

	
}