package com.xavier.extractimage;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class ExtractImage {

    public static void main(String[] args) throws Exception{
        String filePath = "D:\\1.html";
        extractAllImages(filePath);
    }

    public static List<String> extractAllImages(String filePath) throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element root = (Element) document.getRootElement();
        List<String> images = root.selectNodes("//div[@class='WordSection1']");
//p1[@id1='aaaa']/name
        System.out.println(images.size());
        return images;
    }

}
