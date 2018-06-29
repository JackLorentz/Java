package myjava.homework;// Represents a withdrawal ATM transaction

import java.util.Scanner;

public class Loan extends Transaction {

	/* Fill your code here */
    private static Keypad keypad = new Keypad();

    @Override
    public void execute() {
        int loan_MAX = 0;
        System.out.println("You debt : " + super.getBankDatabase().getDebt(super.getAccountNumber()));
        switch (super.getBankDatabase().getCreditLevel(super.getAccountNumber())){
            case 'A':
                loan_MAX = 11000;
                break;
            case 'B':
                loan_MAX = 9000;
                break;
            case 'C':
                loan_MAX = 7000;
                break;
            case 'D':
                loan_MAX = 5000;
                break;
        }
        System.out.print("Tour loan limit is " + loan_MAX + ", How much fo you want to loan : ");
        int loan = keypad.getInput();
        if(loan != -1){
            if(loan > loan_MAX)
                System.out.println("Sorry, You can not loan any money now.");
            else{
                if(super.getBankDatabase().getDebt(super.getAccountNumber()) + loan > loan_MAX)
                    System.out.println("Transaction Error. You have not much Loan Limit.");
                else{
                    super.getBankDatabase().setDebt(super.getAccountNumber(), loan);
                    System.out.println("Transaction Success");
                }
            }
        }
    }
}
