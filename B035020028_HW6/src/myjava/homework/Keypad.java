package myjava.homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Keypad {
    private BufferedReader br;
    private int test = 0;

    // initializes
    public Keypad () {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /*
     *  This function may throw some Exception if the user enters non-integer input.
     *  You must use try...catch to deal with it.
     */
    public int getInput() {
        /* Fill your code here */
        try{
            test = Integer.parseInt(br.readLine());
            if(test > 4)    test = 1;
            return test;
        }
        catch(Exception e){

        }

        return 1;
    }
}
