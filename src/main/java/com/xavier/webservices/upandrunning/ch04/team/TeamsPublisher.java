package com.xavier.webservices.upandrunning.ch04.team;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/22.
 */
public class TeamsPublisher {
    public static void main(String[] args) {
        int port = 8888;
        String url = "http://localhost:" + port + "/teams";
        System.out.println("Publishing Teams restfully on port " + port);
        Endpoint.publish(url, new RestfulTeams());
    }
}
