package com.xavier.webservices.upandrunning.ch01.soap;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Date;

/**
 * Created by Xavier on 2017/11/30.
 */
public class DemoSoap {
    private static final String LocalName = "TimeRequest";
    private static final String Namespace = "http://ch01/mysoap";
    private static final String NamespacePrefix = "ms";

    private ByteArrayOutputStream out;
    private ByteArrayInputStream in;

    public static void main(String[] args) {
        new DemoSoap().request();
    }

    private SOAPMessage create_soap_message( ) {
        SOAPMessage msg = null;
        MessageFactory mf = null;
        try {
            mf = MessageFactory.newInstance();
            msg = mf.createMessage();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private SOAPMessage create_soap_message(InputStream in) {
        SOAPMessage msg = null;
        MessageFactory mf = null;
        try {
            mf = MessageFactory.newInstance();
            msg = mf.createMessage(null, in);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private Name create_qname(SOAPMessage msg) {
        Name name = null;
        SOAPEnvelope env = null;
        try {
            env = msg.getSOAPPart().getEnvelope();
            name = env.createName(LocalName, NamespacePrefix, Namespace);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void trace(String s, SOAPMessage m) {
        System.out.println("\n");
        System.out.println(s);
        try {
            m.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void coordinate_streams ( ) {
        in = new ByteArrayInputStream(out.toByteArray());
        out.reset();
    }

    private void extract_contents_and_print(SOAPMessage msg) {
        try {
            SOAPBody body = msg.getSOAPBody();
            Name lookup_name = create_qname(msg);
            Iterator it = body.getChildElements(lookup_name);
            Node next = (Node) it.next();

            String value = (next == null) ? "Error!" : next.getValue();
            System.out.println("\n\nReturned from server: " + value);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private void process_incoming_soap () {
        try {
            coordinate_streams();;
            SOAPMessage msg = create_soap_message(in);
            Name lookup_name = create_qname(msg);
            SOAPHeader header = msg.getSOAPHeader();
            Iterator it = header.getChildElements(lookup_name);
            Node next = (Node) it.next();
            String value = (next == null) ? "Error!" : next.getValue();

            if(value.toLowerCase().contains("time_request")) {
                String now = new Date().toString();
                SOAPBody body = msg.getSOAPBody();
                body.addBodyElement(lookup_name).addTextNode(now);
                msg.saveChanges();
                msg.writeTo(out);
                trace("The received/processed SOAP message", msg);
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SOAPMessage process_request() {
        process_incoming_soap();
        coordinate_streams();
       return create_soap_message(in);

    }

    private void request() {
        try {
            SOAPMessage msg = create_soap_message();
            SOAPEnvelope env = null;
            env = msg.getSOAPPart().getEnvelope();
            SOAPHeader hdr = env.getHeader();
            Name lookup_name = create_qname(msg);
            hdr.addHeaderElement(lookup_name).addTextNode("time_request");

            out = new ByteArrayOutputStream();
            msg.writeTo(out);
            trace("The sent SOAP message:", msg);
            SOAPMessage response = process_request();
            extract_contents_and_print(response);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
