package myjava.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client implements Runnable{
    private String host;
    private int port;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run(){
        Socket s = null;
        BufferedWriter out = null;
        Random random  = null;

        try{
            for(int i=0; i<5; i++) {
                s = new Socket(host, port);
                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                random = new Random(System.currentTimeMillis() + i);
                //決定要領錢還是存錢
                String tranType = "";
                if(random.nextInt(1000) > 500)
                    tranType = "withdraw";
                else
                    tranType = "deposit";
                //設定交易內容
                transactions.add(new Transaction(
                        i+1,
                        null,
                        random.nextInt(200) + 100,
                        tranType
                ));
                //產生完一筆交易休眠100-300微秒
                try {
                    Thread.sleep(random.nextInt(200) + 100);
                } catch (Exception e) {
                }
                //送出訊息
                String request = transactions.get(i).getNum() + " " + transactions.get(i).getAmount() + " " + transactions.get(i).getTranType();
                out.write(request);
                out.flush();
                s.shutdownOutput();
            }
        }
        catch (IOException e) {
            System.out.println("Socket連線有問題!");
        }


        try{
            //接收訊息
            Scanner in = new Scanner(s.getInputStream(), "UTF-8");
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        }
        catch(IOException e){
            System.out.println("Socket連線有問題!");
        }
        finally{
            try {
                if(out != null) out.close();
                if(s != null) s.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
