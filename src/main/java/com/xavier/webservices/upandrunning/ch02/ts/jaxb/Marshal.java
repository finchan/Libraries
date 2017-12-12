package com.xavier.webservices.upandrunning.ch02.ts.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier on 2017/12/12.
 */
public class Marshal {
    private static final String filename = "bd.mar";
    public static void main(String[] args) {
        new Marshal().run_example();
    }

    private void run_example() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File("").getAbsoluteFile());
        JAXBContext ctx = null;
        try {
            ctx = JAXBContext.newInstance(Skier.class);
            Marshaller ms = ctx.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Skier skier = createSkier();
            ms.marshal(skier, System.out);

            FileOutputStream out = new FileOutputStream(filename);
            ms.marshal(skier, out);
            out.close();

            Unmarshaller ums = ctx.createUnmarshaller();
            File file = new File(filename);
            Skier bd_clone = (Skier) ums.unmarshal(file);
            ms.marshal(bd_clone, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    private Skier createSkier() {
        Person bd = new Person("Bjoern Daehlie", 41, "Male");
        List<String> list = new ArrayList<String>();
        list.add("12 Olympic Medals");
        list.add("9 World Championships");
        list.add("Winningest Winter Olympian");
        list.add("Greatest Nordic Skier");
        return new Skier(bd, "Norway", list);
    }
}
