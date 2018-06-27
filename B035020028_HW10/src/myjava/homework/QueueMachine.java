package myjava.homework;

import java.util.LinkedList;

public class QueueMachine {
    private LinkedList<Transaction> queue = new LinkedList<>();

    public synchronized Transaction get(){
        Transaction temp = queue.getFirst();
        queue.removeFirst();
        return temp;
    }

    public synchronized void add(Transaction newTran){
        this.queue.addLast(newTran);
    }
}
