package myjava.homework;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadHandler implements Runnable{
    private Socket incoming;
    private InputStream inputStream;
    private BufferedWriter out;
    private Transaction transaction;
    private char letter;

    public ThreadHandler(Socket incoming, Transaction transaction, char letter){
        this.incoming = incoming;
        this.transaction = transaction;
        this.letter = letter;
    }

    public void run(){
        try
        {
            //接收訊息
            System.out.println("Clerk " + letter + " 取得交易 Transaction[" +
                                "run: " + transaction.getNum() + "" +
                                ", account = Account[name: Java, balance = " + transaction.getAccount().getBalance() + "]" +
                                ", amount = " + transaction.getAmount() +
                                ", tranType = " + transaction.getTranType() + "]");
            //執行交易前先休眠300微秒
            try{ Thread.sleep(300); } catch (Exception e){}
            if(transaction.getTranType().equals("withdraw")){
                transaction.getAccount().withdraw(transaction.getAmount());
            }
            else{
                transaction.getAccount().deposit(transaction.getAmount());
            }
            String response = "Clerk " + letter + " 完成 " + transaction.getNum() + " 號交易處理, " +
                                "Java帳戶餘額: " + transaction.getAccount().getBalance();
            System.out.println(response);
            //發送訊息
            out = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
            out.write("Service Finished.");
            out.flush();
            incoming.shutdownOutput();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                if(inputStream != null) inputStream.close();
                if(out != null) out.close();
                if(incoming != null) incoming.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
