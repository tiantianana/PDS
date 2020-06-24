package bank.logic;

public class BankException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String toString() {
		return "Insufficient funds in the source account.";
	}
}

//añadir parametros para los distintos errores
//arreglar error, commit, push, repetir