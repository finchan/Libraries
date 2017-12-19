package com.xavier.webservices.upandrunning.ch03.binary.base64service;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/19.
 */
public class SkiImageServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ski", new SkiImageService());
    }
}