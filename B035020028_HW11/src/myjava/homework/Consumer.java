package myjava.homework;

public class Consumer implements Runnable{
    private Store store;
    //初始欲讀取數字
    private int initialNum;
    //消費者編號
    private int num;
    //讀過數字之總和
    private int sum = 0;

    public Consumer(Store store, int initialNum, int num){
        this.store = store;
        this.initialNum = initialNum;
        this.num = num;
    }

    public void run(){
        for(int i=initialNum; i<initialNum+10; i++){
            try{
                Thread.sleep(1000);
                store.consume(num);
                sum += i;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Customer read values totaling: " + sum);
        System.out.println("Terminating Customer");
    }
}
