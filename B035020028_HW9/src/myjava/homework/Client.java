package myjava.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
    public static void main(String args[]){
        Socket s = null;
        BufferedWriter out = null;

        if(args.length != 4){
            System.out.println("Wrong Arguments !");
            exit(1);
        }

        try{
            Integer.parseInt(args[3]);
        }
        catch(Exception e){
            System.out.println("Your port is wrong.");
            exit(1);
        }

        System.out.println("IP: " + args[2] + " Port: " + args[3]);
        System.out.println("Resource requirement : < A: " + args[0] + ", B: " + args[1] + ">");
        System.out.println("Connecting...\n");

        try
        {
            s = new Socket(args[2], Integer.parseInt(args[3]));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            //送出訊息
            String request = args[0] + " " + args[1];
            out.write(request);
            out.flush();
            s.shutdownOutput();
            //接收訊息
            Scanner in = new Scanner(s.getInputStream(), "UTF-8");
           ;while(in.hasNextLine()){
                System.out.println(in.nextLine());
            }
        }
        catch (IOException e){
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
