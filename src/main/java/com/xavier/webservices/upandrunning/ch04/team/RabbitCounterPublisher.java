package com.xavier.webservices.upandrunning.ch04.team;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2018/1/22.
 */
public class RabbitCounterPublisher {
    public static void main (String[] args) {
        String port = "8888";
        String endpoint = "http://localhost:" + port + "/rabbitcounter";
        System.out.println("Publish Restful Service!");
        Endpoint.publish(endpoint, new RabbitCounterProvider());
    }
}
