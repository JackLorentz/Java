package myjava.homework;

public class Producer implements Runnable{
    private Store store;
    //初始欲產生數字
    private int initialNum;
    //生產者編號
    private int num;

    public Producer(Store store, int initialNum, int num){
        this.store = store;
        this.initialNum = initialNum;
        this.num = num;
    }

    public void run(){
        for(int i=initialNum; i<initialNum+10; i++){
            try{
                Thread.sleep(1000);
                store.produce(i, num);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
