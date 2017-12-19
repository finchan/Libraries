package com.xavier.webservices.upandrunning.ch03.binary.base64mimeclient;
import java.util.List;
import java.awt.*;

/**
 * Created by Xavier on 2017-12-19.
 */
public class SkiMimeClient {
    public static void main(String[] args) {
        SkiImageService_Service service = new SkiImageService_Service();
        SkiImageService port = service.getSkiImageServicePort();
        Image image = port.getImage("telemk");
        List<Image> images = port.getImages();
        System.out.println("---------");
    }
}
