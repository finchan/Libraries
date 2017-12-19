package com.xavier.webservices.upandrunning.ch03.binary.base64mimeservice;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Xavier on 2017/12/19.
 */
@WebService(serviceName = "SkiImageService")
public class SkiImageService {
    private static final String[] names = {"nordic.jpg", "telemk.jpg", "alphine.jpg"};
    private Map<String, String> photos;
    private String default_key;

    @WebMethod
    public Image getImage(String name) {
        return createImage(name);
    }

    private Image createImage(String name) {
        byte[] bytes = getRawBytes(name);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Iterator iterators = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader iterator = (ImageReader) iterators.next();
        try{
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            iterator.setInput(iis, true);
            return iterator.read(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getRawBytes(String name) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            String cwd = System.getProperty("user.dir");
            String sep = System.getProperty("file.separator");
            String base_name = cwd+sep+"jpegs" +sep;
            String file_name = base_name + name + ".jpg";
            System.out.println("user.dir " + cwd);
            System.out.println("file.separator " + sep);
            System.out.println("base_name " + base_name);
            System.out.println("filename " + file_name);
            FileInputStream in = new FileInputStream(file_name);

            if(in== null) in = new FileInputStream(base_name + "nordic.jpg");
            byte[] buffer = new byte[2048];
            int n = 0;
            while ((n=in.read(buffer))!=-1)
                out.write(buffer, 0, n);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    @WebMethod
    public List<Image> getImages (){
        return createImageList();
    }

    private List<Image> createImageList() {
        List<Image> list = new ArrayList<Image>();
        Set<String> key_set = photos.keySet();
        for(String key : key_set) {
            Image image = createImage(key);
            if (image != null) list.add(image);
        }
        return list;
    }

    public SkiImageService() {
        photos = new HashMap<String, String>();
        photos.put("nordic", "nordic.jpg");
        photos.put("alpine", "alpine.jpg");
        photos.put("telemk", "telemk.jpg");
        default_key = "nordic";
    }
}
