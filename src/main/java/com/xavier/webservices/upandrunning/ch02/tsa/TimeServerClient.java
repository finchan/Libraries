package com.xavier.webservices.upandrunning.ch02.tsa;

import com.xavier.webservices.upandrunning.ch02.tsa.clienttsa.AnnotatedTimeServer;
import com.xavier.webservices.upandrunning.ch02.tsa.clienttsa.RevisedTimeServer;

/**
 * Created by Xavier on 2017/12/14.
 */
public class TimeServerClient {
    public static void main(String[] args) {
        RevisedTimeServer ts = new RevisedTimeServer();
        AnnotatedTimeServer port = ts.getAnnotatedTimeServerPort();

        System.out.println(port.timeString("Hello World!"));
        System.out.println(port.timeElapsed());
        port.acceptInput("Hello World!");
    }
}
