package myjava.homework;

public class Account {
    private String name;
    private int balance;

    public Account(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int newbalance){
        this.balance = newbalance;
    }

    public synchronized void withdraw(int amount){
        //避免同步問題,要上互斥鎖
        setBalance(this.balance - amount);
    }

    public synchronized void deposit(int amount){
        //避免同步問題,要上互斥鎖
        setBalance(this.balance + amount);
    }
}
