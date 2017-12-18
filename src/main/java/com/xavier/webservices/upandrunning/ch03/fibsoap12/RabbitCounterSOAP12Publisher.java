package com.xavier.webservices.upandrunning.ch03.fibsoap12;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017-12-18.
 */
public class RabbitCounterSOAP12Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/rcs12", new RabbitCounterSOAP12());
    }
}
