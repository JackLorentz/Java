package myjava.homework;

public class Screen {
	
	//display a message without a carriage return
	public void displayMessage (String message) {
        /* Fill your code here */
		System.out.println(message);
		System.out.println("1. View my balance");
		System.out.println("2. Withdraw");
		System.out.println("3. Deposit");
		System.out.println("4. Loan");
		System.out.println("5. Exit");
		System.out.print("Enter a choice : ");
	}
	
	//display a message with a carriage return
	public void displayMessageLine (String message) {
		/* Fill your code here */
	}
	
}
