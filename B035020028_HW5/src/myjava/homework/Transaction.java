package myjava.homework;// Abstract superclass Transaction represents an ATM transaction

public abstract class Transaction {
	
	/* Fill your code here */
	private BankDatabase bankDatabase;
	private int accountNumber;

    public abstract void execute();

	public int getAccountNumber(){
	    return this.accountNumber;
    };

	public BankDatabase getBankDatabase(){
	    return  this.bankDatabase;
    }

    public void loadAccountInfo(BankDatabase bankDatabase, int accountNumber){
        this.bankDatabase = bankDatabase;
        this.accountNumber = accountNumber;
    }

    public BankDatabase uploadAccountInfo(){
        return bankDatabase;
    }
}
