package myjava.homework;//Represents a bank account

public class Account {
	
	/* Fill your code here */
	private int accountNumber;
	private int pin;
	private int totalBalance;
	private int debt;
	private char credit_level;

	public Account(int accountNumber, int pin, int totalBalance, int debt, char credit_level){
	    this.accountNumber = accountNumber;
	    this.pin = pin;
	    this.totalBalance = totalBalance;
	    this.debt = debt;
	    this.credit_level = credit_level;
    }

    public boolean validatePIN(int pin){
	    return this.pin == pin;
    }

    public int getAccountNumber(){
	    return this.accountNumber;
    }

    public int getTotalBalance(){
	    return this.totalBalance;
    }

    public char getCreditLevel(){
	    return this.credit_level;
    }

    public int getDebt(){
	    return this.debt;
    }

    public void setTotalBalance(int money){
	    this.totalBalance += money;
    }

    public void setDebt(int loan){
	    this.debt += loan;
    }
}
