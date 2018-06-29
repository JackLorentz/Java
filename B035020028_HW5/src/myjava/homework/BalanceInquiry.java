package myjava.homework;// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction {

	/* Fill your code here */
    @Override
    public void execute() {
        System.out.println("Balance Information");
        System.out.println("Total Balance : " + super.getBankDatabase().getTotalBalance(super.getAccountNumber()));
    }
}
