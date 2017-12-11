package com.xavier.webservices.upandrunning.ch01.ts;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Xavier on 2017/11/30.
 */
public class TimeClient {
    public static void main(String[] args) throws MalformedURLException {
//        URL url = new URL("http://127.0.0.1:8080/ts?wsdl"); //tcpmon monitor
        URL url = new URL("http://localhost:9876/ts?wsdl");
        QName qName = new QName("http://ts.ch01.upandrunning.webservices.xavier.com/", "TimeServerImplService");
        Service service = Service.create(url, qName);
        TimeServer eif = service.getPort(TimeServer.class);
        System.out.println(eif.getTimeAsString());
        System.out.println(eif.getTimeAsEclapsed());
    }
}
