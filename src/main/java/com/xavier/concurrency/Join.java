package com.xavier.concurrency;

import com.xavier.utilities.DI;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;


/**
 * Created by Xavier on 2017/6/5.
 */
public class Join {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        Logger logger = Logger.getLogger(Domino.class);
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        public void run() {
            try{
                thread.join();
            } catch (InterruptedException e) {

            }
            DI.info(logger, Thread.currentThread().getName() + " terminate.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
