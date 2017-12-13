package com.xavier.webservices.upandrunning.ch02.contractfirst.rewriteservice;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/13.
 */
public class TempConvertPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/tempconverter", new TempConvert());
    }
}
