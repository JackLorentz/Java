package myjava.homework;// Represents a withdrawal ATM transaction

import java.util.Scanner;

public class Withdrawal extends Transaction{

	/* Fill your code here */
    private static Keypad keypad = new Keypad();

    @Override
    public void execute() {
        System.out.print("How much do you want to withdraw : ");
        int withdraw = keypad.getInput();
        if(withdraw != -1){
            if (super.getBankDatabase().getTotalBalance(super.getAccountNumber()) - withdraw >= 0){
                super.getBankDatabase().setTotalBalance(super.getAccountNumber(),  0 - withdraw);
                System.out.println("Transaction Success");
            }
            else{
                System.out.println("Transaction Error. You don't have enough money.");
            }
        }
    }
}
