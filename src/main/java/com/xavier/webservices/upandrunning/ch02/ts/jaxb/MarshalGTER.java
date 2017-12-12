package com.xavier.webservices.upandrunning.ch02.ts.jaxb;

import com.xavier.webservices.upandrunning.ch02.ts.docclient.GetTimeAsEclapsedResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.Date;

/**
 * Created by Xavier on 2017/12/12.
 */
public class MarshalGTER {
    private static final String fileName = "gter.mar";

    public static void main(String[] args) {
        new MarshalGTER().run_example();;
    }

    private void run_example() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(GetTimeAsEclapsedResponse.class);
            Marshaller ms = ctx.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            GetTimeAsEclapsedResponse resp = new GetTimeAsEclapsedResponse();
            resp.setReturn(new Date().getTime());

            ms.marshal(resp, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
