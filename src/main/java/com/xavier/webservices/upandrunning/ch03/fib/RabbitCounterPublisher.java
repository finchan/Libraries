package com.xavier.webservices.upandrunning.ch03.fib;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/17.
 */
public class RabbitCounterPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/fib", new RabbitCounter());
    }
}
