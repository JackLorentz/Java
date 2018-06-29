package myjava.homework;

import java.io.IOException;
import java.util.Scanner;

public class ATM {

    private static Scanner scanner = new Scanner(System.in);
    private static Keypad keypad = new Keypad();
	private static BankDatabase bankDatabase = new BankDatabase();
    private static boolean userAuthenticated, EXIT = false;
    private static int accountNumber;

    /* Fill your code here */
    public static void run(){
        while(true){
            EXIT = false;
            while(true){
                System.out.println("Welcome!");
                System.out.print("Please enter your account number : ");
                accountNumber = keypad.getInput();
                if(accountNumber != -1){
                    System.out.print("Please enter your pin : ");
                    if(userAuthenticated = bankDatabase.authenticateUser(accountNumber, keypad.getInput()))
                        break;
                    else
                        System.out.println("AccountNumber or Pin Error");
                }
                System.out.println();
            }

            while(true){
                new Screen().displayMessage("\nMain_menu");
                int opt = keypad.getInput();
                if(opt != -1){
                    switch(opt){
                        case 1:
                            BalanceInquiry balanceInquiry = new BalanceInquiry();
                            balanceInquiry.loadAccountInfo(bankDatabase, accountNumber);
                            balanceInquiry.execute();
                            break;
                        case 2:
                            Withdrawal withdrawal = new Withdrawal();
                            withdrawal.loadAccountInfo(bankDatabase, accountNumber);
                            withdrawal.execute();
                            bankDatabase = withdrawal.uploadAccountInfo();
                            break;
                        case 3:
                            Deposit deposit = new Deposit();
                            deposit.loadAccountInfo(bankDatabase, accountNumber);
                            deposit.execute();
                            bankDatabase = deposit.uploadAccountInfo();
                            break;
                        case 4:
                            Loan loan = new Loan();
                            loan.loadAccountInfo(bankDatabase, accountNumber);
                            loan.execute();
                            bankDatabase = loan.uploadAccountInfo();
                            break;
                        case 5:
                            EXIT = true;
                            break;
                        default:
                            System.out.println("Wrong choice, please input again.");
                            break;
                    }
                    System.out.println();
                    if(EXIT)    break;
                }
            }
        }
    }
}
