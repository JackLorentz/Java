package myjava.homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Store {
    //實際buffer狀況,並用queue的方式儲存
    private LinkedList<Integer> products = new LinkedList<Integer>();
    //紀錄buffer每個cell裝過的數字(不會削掉數字)
    private Integer[] record = null;
    //共用buffer容量
    private int CAPACITY;
    //因為Java沒有Monitor這類別或介面可以直接使用, 所以我們要用lock和Condition來實作
    //(類似Semaphore實現Monitor的道理, 不過Java有提供Condition的變數型態)
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    //記錄第N個consumer的位置
    private int[] consumer = {0, 0};
    //記錄第N個producer的位置
    private int[] producer = {0, 0};

    public Store(int CAPACITY){
       this.CAPACITY = CAPACITY;
       this.record = new Integer[CAPACITY];
       for(int i=0; i<CAPACITY; i++){
           record[i] = -1;
       }
    }

    public void produce(Integer product, int num) throws InterruptedException{
        //進入monitor(確保互斥存取)
        lock.lock();
        try{
            //buffer滿了就先卡住, 該thread到queue(FIFO排班原則)中排隊(Condition變數的特性)
            while(products.size() == CAPACITY) {
                System.out.println("Buffer is full. Producer waits");
                full.await();
            }
            //加入一數字進buffer
            boolean isAdded = products.offer(product);
            if(isAdded){
                //紀錄數字加入位置
                record[producer[num]] =  product;
                //前進下一個要加入的位置
                producer[num] = (producer[num] + 1) % CAPACITY;
                //印出所有thread狀態
                System.out.println("Producer writes " + product + " (buffer cells occupied: " + products.size() + ")");
                System.out.print("buffer cells:    ");
                for(int i=0; i<CAPACITY; i++) {
                    if(record[i] > 9 || record[i] == -1)
                        System.out.print(record[i] + "   ");
                    else
                        System.out.print(" " + record[i] + "   ");
                }
                System.out.println();
                System.out.print("                 ");
                for (int i=0; i<CAPACITY; i++){
                    System.out.print("---- ");
                }
                System.out.println();
                System.out.print("                 ");
                for(int i=0; i<CAPACITY; i++){
                    printOutPosition(i);
                }
                System.out.println();
                if(product == 10 || product == 20){
                    System.out.println("Producer done producing.");
                    System.out.println("Terminating Producer");
                }
                //允許empty的waiting queue中的一個thread進入monitor
                empty.signal();
            }
        }
        finally {
            //無論如何都會離開monitor
            lock.unlock();
        }
    }


    public void consume(int num) throws InterruptedException{
        //進入monitor(確保互斥存取)
        lock.lock();
        try{
            //buffer空了先卡住, 該thread到queue(FIFO排班原則)中排隊(Condition變數的特性)
            while(products.size() == 0){
                System.out.println("Buffer is empty. Consumer waits.");
                empty.await();
            }
            //從buffer中拿出一個數字
            Integer value = products.poll();
            if(value != null){
                //位置前進到下一個要讀取的位置
                consumer[num] = (consumer[num] + 1) % CAPACITY;
                //印出所有thread狀態
                System.out.println("Customer reads " + value + " (buffer cells occupied: " + products.size() + ")");
                System.out.print("buffer cells:    ");
                for(int i=0; i<CAPACITY; i++){
                    if(record[i] > 9 || record[i] == -1)
                        System.out.print(record[i] + "   ");
                    else
                        System.out.print(" " + record[i] + "   ");
                }
                System.out.println();
                System.out.print("                 ");
                for (int i=0; i<CAPACITY; i++){
                    System.out.print("---- ");
                }
                System.out.println();
                System.out.print("                 ");
                for(int i=0; i<CAPACITY; i++){
                    printOutPosition(i);
                }
                System.out.println();
                if(value == 10 || value == 20){
                    System.out.println("Producer done producing.");
                    System.out.println("Terminating Producer");
                }
                //允許full的waiting queue中的一個thread進入monitor
                full.signal();
            }
        }
        finally {
            //無論如何都會離開monitor
            lock.unlock();
        }
    }
    //用來印出每一個thread的位置
    public void printOutPosition(int i){
        if(i == consumer[1] && i == producer[1] && i == consumer[0] && i == producer[0]){
            System.out.print("WRWR ");
        }
        else if((i == consumer[1] && i == producer[1] && i == consumer[0]) ||
                (i == consumer[1] && i == producer[0] && i == consumer[0])){
            System.out.print("RWR  ");
        }
        else if((i == producer[1] && i == consumer[1] && i == producer[0]) ||
                (i == producer[1] && i == consumer[0] && i == producer[0])){
            System.out.print("WRW  ");
        }
        else if((i == consumer[0] && i == producer[0])
                || (i == consumer[1] && i == producer[0])
                || (i == consumer[0] && i == producer[1])
                || (i == consumer[1] && i == producer[1])){
            System.out.print(" WR  ");
        }
        else if((i == producer[1] && i == producer[0])){
            System.out.print(" WW  ");
        }
        else if((i == consumer[1] && i == consumer[0])){
            System.out.print(" RR  ");
        }
        else{
            if(i == producer[0] || i == producer[1])
                System.out.print(" W   ");
            else if(i == consumer[0] || i == consumer[1])
                System.out.print(" R   ");
            else
                System.out.print("     ");
        }
    }
}
