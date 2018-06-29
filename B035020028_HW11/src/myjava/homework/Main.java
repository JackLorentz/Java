package myjava.homework;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        System.out.print("輸入buffer容量: ");
        int capacity = scanner.nextInt();
        //共用buffer
        Store store = new Store(capacity);
        //初始狀態表示
        System.out.println("Initial State (buffer cells occupied: 0)");
        System.out.print("buffer cells:    ");
        for(int i=0; i<capacity; i++) {
            System.out.print("-1   ");
        }
        System.out.println();
        System.out.print("                 ");
        for (int i=0; i<capacity; i++){
            System.out.print("---- ");
        }
        System.out.println("\n                 WRWR");

        //兩對消費者與生產者
        //參數: 共用變數 , 初始數字 , 編號
        (new Thread(new Producer(store, 1, 0))).start();
        (new Thread(new Consumer(store, 1, 0))).start();
        (new Thread(new Producer(store, 11, 1))).start();
        (new Thread(new Consumer(store, 11, 1))).start();
    }
}
