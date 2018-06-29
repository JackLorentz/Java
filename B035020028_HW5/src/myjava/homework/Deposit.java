package myjava.homework;// Represents a deposit ATM transaction

import java.util.Scanner;

public class Deposit extends Transaction{
	
	/* Fill your code here */
    private static Keypad keypad = new Keypad();

    @Override
    public void execute() {
        System.out.print("How much do you want to deposit : ");
        int deposit = keypad.getInput();
        if(deposit != -1){
            super.getBankDatabase().setTotalBalance(super.getAccountNumber(),  deposit);
            System.out.println("Transaction Success");
        }
    }
}
