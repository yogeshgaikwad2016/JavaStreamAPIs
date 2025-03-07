package com.interview.multithreading;

public class RaceConditionEx extends Thread {

    synchronized void print() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Count: " + i);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        print();
    }

    public static void main(String[] args) {
        RaceConditionEx o = new RaceConditionEx();

        Thread t1 = new Thread(o);
        Thread t2 = new Thread(o);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }
    }
}
