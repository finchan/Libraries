package com.xavier.webservices.upandrunning.ch02.ts;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/5.
 */
public class TimeServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:9876/ts", new TimeServerImpl());
    }
}
