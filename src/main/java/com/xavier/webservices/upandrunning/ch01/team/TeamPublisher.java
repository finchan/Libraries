package com.xavier.webservices.upandrunning.ch01.team;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/4.
 */
public class TeamPublisher {
    public static void main(String[] args) {
        int port = 8888;
        String url = "http://localhost:" +port+"/teams";
        System.out.println("Publishing Teams on port " + port);
        Endpoint.publish(url, new Teams());
    }
}