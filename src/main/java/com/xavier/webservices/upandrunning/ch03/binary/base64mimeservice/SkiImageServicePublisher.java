package com.xavier.webservices.upandrunning.ch03.binary.base64mimeservice;

import com.xavier.webservices.upandrunning.ch03.binary.base64service.SkiImageService;

import javax.xml.ws.Endpoint;

/**
 * Created by Xavier on 2017/12/19.
 */
public class SkiImageServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ski", new SkiImageService());
    }
}