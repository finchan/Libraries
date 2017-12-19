package com.xavier.webservices.upandrunning.ch03.binary.mtomattachmentclient;

import javax.activation.DataHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * Created by Xavier on 2017-12-19.
 */
public class MTOMClient {
    public static void main(String [] args) {
        SkiImageService_Service service = new SkiImageService_Service();
        SkiImageService port = service.getSkiImageServicePort();
        DataHandler image = port.getImage("nordic");
        List<DataHandler> images = port.getImages();
        dump(image);
        for (DataHandler hd: images) {
            dump(hd);
        }

        try {
            //Image imageResult = (BufferedImage) image.getContent();
            InputStream stream = image.getInputStream();
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream("aa.png");
            while(stream.read(buffer) != -1) {
                fos.write(buffer);
            }
            stream.close();
            fos.close();

            System.out.println("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dump(DataHandler dh) {
        System.out.println();
        try{
            System.out.println("MIME TYPE: " +dh.getContentType() );
            System.out.println("Content: " + dh.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
