package myjava.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class Server implements Runnable{
    private static QueueMachine queueMachine = new QueueMachine();
    private static  Account account = new Account("Java", 5000);
    private int port;
    private char[] clerks;
    private int clerkTotalNum;
    //Thread Pooling
    private ExecutorService threadPool;

    public Server(int port, char[] clerks){
        this.port = port;
        this.clerks = clerks;
        clerkTotalNum = clerks.length;
        threadPool = Executors.newFixedThreadPool(clerkTotalNum);
    }

    public void run(){
            try(ServerSocket s = new ServerSocket(port)){
                //客戶(交易)要抽的號碼牌
                int clerkNum = 0;

                while(true){
                    Socket incoming = s.accept();
                    //監聽收到交易要求
                    InputStream inputStream = incoming.getInputStream();
                    Scanner in = new Scanner(inputStream, "UTF-8");
                    String[] request;
                    while(in.hasNextLine()) {
                        //分析要求內容
                        request = in.nextLine().split(" ");
                        Transaction transaction = new Transaction(
                                Integer.parseInt(request[0]),
                                account,
                                Integer.parseInt(request[1]),
                                request[2]
                        );
                        System.out.println("Transaction[run: " + transaction.getNum() +
                                            ", account = " + "Account[name: Java, " + "balance = " + account.getBalance() + "]" +
                                            ", amount = " + transaction.getAmount() +
                                            ", tranType = " + transaction.getTranType() + "]");
                        //員工有限交易必須排隊等待
                        queueMachine.add(transaction);
                        Runnable r = new ThreadHandler(incoming, queueMachine.get(), clerks[clerkNum]);
                        threadPool.execute(r);
                        //Thread clerk = new Thread(r);
                        //clerk.start();
                        clerkNum = (clerkNum+1) % clerkTotalNum;
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }
}
