package com.xavier.webservices.upandrunning.ch01.ts;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/4.
 */
public class TimePublisherMT {
    private Endpoint endpoint;

    public static void main(String[] args) {
        TimePublisherMT self = new TimePublisherMT();
        self.create_endpoint();
        self.configure_endpoint();
        self.publish();
    }

    private void create_endpoint() {
        endpoint = Endpoint.create(new TimeServerImpl());
    }

    private void configure_endpoint() {
        endpoint.setExecutor(new MyThreadPool());
    }

    private void publish() {
        int port = 8888;
        String url = "http://localhost:" + port + "/ts";
        endpoint.publish(url);
        System.out.println("Publishing TimeServer on Port " +port);
    }
}
