package com.xavier.webservices.upandrunning.ch01.ts;


import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/11/30.
 */
public class TimeServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:9876/ts", new TimeServerImpl());
    }
}
