package myjava.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

public class Server {
    public static void main(String args[]){

            if(args.length != 3){
                System.out.println("Wrong Arguments !");
                exit(1);
            }

            try{
                Integer.parseInt(args[2]);
            }
            catch(Exception e){
                System.out.println("Your port is wrong.");
               exit(1);
            }

            System.out.println("Port: " + args[2]);
            System.out.println("Resource: < A: " + args[0] + ", B: " + args[1] + ">");
            int[] resource = new int[2];
            for(int i=0; i<resource.length; i++){
                resource[i] = Integer.parseInt(args[i]);
            }

            try(ServerSocket s = new ServerSocket(Integer.parseInt(args[2]))){
                int i=1;
                System.out.println("Listening Now");

                while(true){
                    Socket incoming = s.accept();
                    Runnable r = new ThreadHandler(incoming, i, resource);
                    Thread t = new Thread(r);
                    t.start();
                    i++;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }
}
