package com.xavier.webservices.upandrunning.ch03.binary.mtomattachmentservice;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Created by Xavier on 2017-12-19.
 */
public class MTOMPublisher {
    private Endpoint endpoint;

    public static void main(String[] args) {
        MTOMPublisher publisher = new MTOMPublisher();
        publisher.create_endpoint();
        publisher.config_endpoint();
        publisher.publish_endpoint();
    }

    private void create_endpoint() {
        endpoint = Endpoint.create(new SkiImageService());
    }

    private void config_endpoint() {
        SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
        binding.setMTOMEnabled(true);
    }

    private void publish_endpoint() {
        int port = 9999;
        String url = "http://localhost:" + port + "/ski";
        endpoint.publish(url);
        System.out.println(url);
    }
}
