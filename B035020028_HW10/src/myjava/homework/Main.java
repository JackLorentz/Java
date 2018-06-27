package myjava.homework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        int num = 0;
        System.out.print("請輸入員工數(Thread數): ");
        if((num = scanner.nextInt()) > 0){
            char clerk = 'A';
            char[] clerks = new char[num];
            for(int i=0; i<num; i++){
                clerks[i] = clerk;
                clerk ++;
            }
            (new Thread(new Server(53028, clerks))).start();
            (new Thread(new Client("127.0.0.1", 53028))).start();
        }
    }
}
