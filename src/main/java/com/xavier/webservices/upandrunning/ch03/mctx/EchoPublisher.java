package com.xavier.webservices.upandrunning.ch03.mctx;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/19.
 */
public class EchoPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/echo", new Echo());
    }
}
