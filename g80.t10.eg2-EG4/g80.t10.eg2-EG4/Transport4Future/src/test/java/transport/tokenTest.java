//Transport4Future
//Checks Java source code for adherence to a set of rules.
//Copyright (C) 2020

package transport;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;



class tokenTest {
	
	private TokenManager myManager;
	private String jsonFilesFolder;
	
	public tokenTest() {
		
		jsonFilesFolder = System.getProperty("user.dir") + "/JsonFile/TokenRequest/";
		
		myManager = new TokenManager();
	}

	@DisplayName("File exits and token found")
	@Test
	void correctTokenTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_01.json";
		String expectedToken = " a6bbb8051ca3e1f45026d44ddaddda9";
		String obtainedToken = myManager.tokenRequestGeneration(filePath);
		assertEquals(expectedToken, obtainedToken);
	}
	
	
	@DisplayName("File is missing")
	@Test
	void filesIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_02.json";
		String expectedMessage = "Error: file not found";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	

	@DisplayName("Missing Bracket")
	@Test
	void missingBracketTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_04.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("Duplicate Variables")
	@Test
	void duplicateVariablesTest() throws TokenManagementException {
		String expectedMessage = "";
		String filePath = this.jsonFilesFolder + "CR_RF1_05.json";
		try {
			expectedMessage = myManager.tokenRequestGeneration(filePath);	
		} catch (TokenManagementException ex) {
			ex.printStackTrace();
		} 
		assertEquals(" a6bbb8051ca3e1f45026d44ddaddda9", expectedMessage  );
	}
	
	@DisplayName("Duplicate one Variable")
	@Test
	void duplicateAVariableTest() throws TokenManagementException {
		String expectedMessage = "";
		String filePath = this.jsonFilesFolder + "CR_RF1_07.json";
		try {
			expectedMessage = myManager.tokenRequestGeneration(filePath);	
		} catch (TokenManagementException ex) {
			ex.printStackTrace();
		} 
		assertEquals(" a6bbb8051ca3e1f45026d44ddaddda9", expectedMessage  );
	}
	
	/***********************************Device Name****************************************/
	@DisplayName("Missing Device Name")
	@Test
	void missingDeviceNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_06.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}


	@DisplayName("Device Name existe pero el valor no existe")
	@Test
	void missingButExistsDeviceNameTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_08.json";
		String expectedMessage = "Error: Json can not be created due to incorrect representation";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}

	@DisplayName("La longitud del campo Device Name es mayor que 20 caracteres")
	@Test
	void stringDeviceLongTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_09.json";
		String expectedMessage = ("Error: Invalid String lenght for device name");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	
	@DisplayName("La longitud del campo Device Name es de 1 caracteres")
	@Test
	void stringDevice1TokenTest() throws TokenManagementException {
		String expectedMessage  = "";
		String filePath = this.jsonFilesFolder + "CR_RF1_23.json";
		try {
			expectedMessage = myManager.tokenRequestGeneration(filePath);	
		} catch (TokenManagementException ex) {
			ex.printStackTrace();
		} 
		assertEquals("fbe4673cf4f27a825e5f2782a23f016f", expectedMessage  );
	}
	
	@DisplayName("La longitud del campo Device Name es de 20 caracteres")
	@Test
	void stringDevice20TokenTest() throws TokenManagementException {
		String expectedMessage = "";
		String filePath = this.jsonFilesFolder + "CR_RF1_24.json";
		try {
			expectedMessage = myManager.tokenRequestGeneration(filePath);	
		} catch (TokenManagementException ex) {
			ex.printStackTrace();
		} 
		assertEquals("c02e1d975f165131ffe304a13913cdd3", expectedMessage );
	}
	
	
	/*****************************Type of Device**********************************/
	@DisplayName("Missing Type Of Device field")
	@Test
	void missingTypeOfDeviceFieldTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_10.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	@DisplayName("Type of device existe pero el valor no existe")
	@Test
	void missingButExistsTypeOfDeviceTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_11.json";
		String expectedMessage = "Error: Invalid Type of Device";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	

	@DisplayName("El valor de type of device es incorrecto")
	@Test
	void stringTypeOfDeviceTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_03.json";
		String expectedMessage = ("Error: Invalid Type of Device");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	

	@DisplayName("El valor de type of device es correcto con Sensor")
	@Test
	void stringTypeOfDeviceSensorTest() throws TokenManagementException {
		String expectedMessage = "";
		String filePath = this.jsonFilesFolder + "CR_RF1_25.json";
		try {
			expectedMessage = myManager.tokenRequestGeneration(filePath);	
		} catch (TokenManagementException ex) {
			ex.printStackTrace();
		} 
		assertEquals("30e80e5b02f6e0f2e2f5185e9644b5e0", expectedMessage );
	}
	
	/*******************************Driver Version*****************************************/
	@DisplayName("Missing Driver Version field")
	@Test
	void missingDriverVersionFieldTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_12.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("Driver Version existe pero el valor no existe")
	@Test
	void missingButExistsDriverVersionTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_13.json";
		String expectedMessage = "Error: Invalid String driver version";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("El valor de Driver Version es incorrecto too short")
	@Test
	void stringDriverVersionShortTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_26.json";
		String expectedMessage = ("Error: Invalid String driver version");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
	
	@DisplayName("El valor de Driver Version es incorrecto too long")
	@Test
	void stringDriverVersionLongTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_28.json";
		String expectedMessage = ("Error: Invalid String driver version");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
		
	
	/***********************Suport Email*****************************/
	@DisplayName("Missing Support Email field")
	@Test
	void missingSupportEMailFieldTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_14.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("Support Email existe pero el valor no existe")
	@Test
	void missingButExistsSupportEmailTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_15.json";
		String expectedMessage = "Error: Invalid Email format";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
		
	/****************************Serial Number***************************************/
	@DisplayName("Missing Serial Number field")
	@Test
	void missingSerialNumberFieldTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_16.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
		
		
	@DisplayName("Serial Number existe pero el valor no existe")
	@Test
	void missingButExistsSerialNumberTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_17.json";
		String expectedMessage = "Error: Invalid serial number";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("El valor de Serial Number es incorrecto")
	@Test
	void stringSerialNumberTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_20.json";
		String expectedMessage = ("Error: Invalid serial number");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
	
	
	@DisplayName("El valor de Serial Number es correcto con barras")
	@Test
	void stringSerialNumberBarrasTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_21.json";
		String expectedMessage = ("Error: Invalid serial number");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
	
	@DisplayName("El valor de Serial Number es incorrecto con space")
	@Test
	void stringSerialNumberSpaceTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_22.json";
		String expectedMessage = ("Error: Invalid serial number");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
		
	/***********************Mac Adress************************************/
	@DisplayName("Missing Mc Adress field")
	@Test
	void missingMcAdressFieldTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_18.json";
		String expectedMessage = "Error: Invalid input data in JSON structure";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("Mc Adress existe pero el valor no existe")
	@Test
	void missingButExistsMcAdressTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_19.json";
		String expectedMessage = "Error: Invalid MAC Address";
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
		
	@DisplayName("El valor de Mac Adress es incorrecto")
	@Test
	void stringMacAdressTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_29.json";
		String expectedMessage = ("Error: Invalid MAC Address");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
	
	
	@DisplayName("El valor de MacAdress es incorrecto")
	@Test
	void stringSupportEmailTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "CR_RF1_19.json";
		String expectedMessage = ("Error: Invalid MAC Address");
		try {
			TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
				myManager.tokenRequestGeneration(filePath);
			});
			assertEquals(expectedMessage, ex.getMessage());
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}	
		
		
		
}
