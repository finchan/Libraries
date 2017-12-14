package com.xavier.webservices.upandrunning.ch02.tsa;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/14.
 */
public class TimeServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/tsa", new TimerServer());
    }
}
