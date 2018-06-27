package myjava.homework;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadHandler implements Runnable{
    private Socket incoming;
    private InputStream inputStream;
    private BufferedWriter out;
    private int number;
    private int[] resource;

    public ThreadHandler(Socket incomingSocket, int number, int[] r){
        this.incoming = incomingSocket;
        this.number = number;
        this.resource = r;
    }

    public synchronized void consumeAndProduce(){
        try
        {
            //接收訊息
            inputStream = incoming.getInputStream();
            Scanner in = new Scanner(inputStream, "UTF-8");
            String[] splitStr;
            int[] req = new int[2];
            while(in.hasNextLine()){
                splitStr = in.nextLine().split(" ");
                for(int i=0; i<2; i++){
                    req[i] = Integer.parseInt(splitStr[i]);
                }
            }
            //發送訊息
            out = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
            if(req[0] > resource[0] || req[1] > resource[0]) {
                System.out.println("[Client_" + number + "] : Resource insufficient! Error...");
                out.write("[Error]: Resource quantity insufficient");
                System.out.println("Replenishment -> A: 0, B: 0");
            }
            else{
                System.out.println("[Client_" + number + "] : Take Resource -> A: " + req[0] + ", B: " + req[1]);
                System.out.println("                  Resource: A:" + (resource[0]-req[0]) + ", B: " + (resource[1]-req[1]));
                System.out.println("Replenishment -> A:" + req[0] + ", B: " + req[1]);
                out.write("Service finish");
            }
            System.out.println("                  Resource: A:" + resource[0] + ", B: " + resource[1]);
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

    public void run(){
        consumeAndProduce();
    }
}
