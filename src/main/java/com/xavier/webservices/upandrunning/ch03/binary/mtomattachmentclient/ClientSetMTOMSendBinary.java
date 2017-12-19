package com.xavier.webservices.upandrunning.ch03.binary.mtomattachmentclient;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Created by Xavier on 2017-12-19.
 */
public class ClientSetMTOMSendBinary {
    public static void main(String[] args) {
        SkiImageService_Service service = new SkiImageService_Service();
        SkiImageService port = service.getSkiImageServicePort();
        BindingProvider bp = (BindingProvider) port;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);
        //If the client needs to send large amounts of binary data to the web service.
        //Client can could also enable MTOM.
    }
}
