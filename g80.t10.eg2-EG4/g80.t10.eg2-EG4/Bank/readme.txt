La clase Account est� formada por las funciones deposit, withdraw, y transferFunds.

void deposit(float amount): ingresa en la cuenta la cantidad indicada.
void withdraw(float amount): retira de la cuenta la cantidad indicada.
void transferFunds(Account destination, float amount): transfiere desde la cuenta a la cuenta destino la cantidad indicada.
	Si una transferencia provoca que la cuenta se quede con menos fondos que la cantidad m�nima (10 unidades monetarias), salta una excepci�n del tipo BankException.



Ejercicio:
Dise�ar, codificar y ejecutar pruebas unitarias para validar que la clase Account es correcta.
