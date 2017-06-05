package com.xavier.concurrency;

import com.xavier.utilities.DI;
import org.apache.log4j.Logger;

/**
 * Created by Xavier on 2017/5/19.
 */
public class DeadLock {
    private final static Logger logger = Logger.getLogger(DeadLock.class);
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    public void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A) {
                    try{
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        DI.info(logger, "1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    synchronized (A){
                        DI.info(logger, "2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
