package myjava.homework;// Represents the bank account information database

public class BankDatabase {

	private Account[] accounts; // array of Accounts

	// no-argument BankDatabase constructor initializes accounts
	public BankDatabase() {
		accounts = new Account[4];  // just 4 accounts for testing
		accounts[0] = new Account(111, 222, 5000, 0, 'A');
		accounts[1] = new Account(222, 333, 4000, 0, 'B');
		accounts[2] = new Account(333, 444, 3000, 0, 'C');
		accounts[3] = new Account(444, 555, 2000, 0, 'D');
	}

	/* Fill your code here */
	public boolean authenticateUser(int accountNumber, int pin) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account.validatePIN(pin);
			}
		}
		return false;
	}

	public int getTotalBalance(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account.getTotalBalance();
			}
		}
		return -1;
	}

	public char getCreditLevel(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account.getCreditLevel();
			}
		}
		return 'E';
	}

	public int getDebt(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account.getDebt();
			}
		}
		return -1;
	}

	public void setTotalBalance(int accountNumber, int money) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				account.setTotalBalance(money);
			}
		}
	}

	public void setDebt(int accountNumber, int loan) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				account.setDebt(loan);
			}
		}
	}
}
