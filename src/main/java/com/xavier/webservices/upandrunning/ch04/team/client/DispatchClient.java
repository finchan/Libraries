package com.xavier.webservices.upandrunning.ch04.team.client;

import com.xavier.webservices.upandrunning.ch04.team.NSResolver;

import javax.xml.namespace.QName;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Xavier on 2018/1/22.
 */
public class DispatchClient {
    public static void main(String[] args) {
        new DispatchClient().setup_and_test();
    }

    private void setup_and_test() {
        URI ns_URI = null;
        try {
            ns_URI = new URI("urn:fib");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        QName service_name = new QName("rcService", ns_URI.toString());
        QName port = new QName("rcPort", ns_URI.toString());
        String endpoint = "http://localhost:8888/rabbitcounter";

        Service service = Service.create(service_name);
        service.addPort(port, HTTPBinding.HTTP_BINDING, endpoint);
        Dispatch<Source> dispatch = service.createDispatch(port, Source.class, Service.Mode.PAYLOAD);

        String xml_start = "<fib:request xmlns:fib='urn:fib'>";
        String xml_end = "</fib:request>";

        List<Integer> nums = new ArrayList<Integer>();
        for(int i=0; i< 12; i++) {
            nums.add(i+1);
        }
        String xml = xml_start + nums.toString() + xml_end;
        invoke(dispatch, "POST", ns_URI.toString(), xml);


        invoke(dispatch, "GET", ns_URI.toString(), null);

        invoke(dispatch, "DELETE", ns_URI.toString(), null);

        invoke(dispatch, "GET", ns_URI.toString(), null);

        nums = new ArrayList<Integer> ();
        for(int i = 0; i < 24; i++) {
            nums.add(i+1);
        }
        xml = xml_start+ nums.toString()+xml_end;
        invoke(dispatch, "POST", ns_URI.toString(), xml);
        invoke(dispatch, "GET", ns_URI.toString(), null);
    }

    private void invoke(Dispatch<Source> dispatch,
                        String verb,
                        String uri,
                        Object data) {
        Map<String, Object> request_context = dispatch.getRequestContext();
        request_context.put(MessageContext.HTTP_REQUEST_METHOD, verb);
        System.out.println("Request: " + data);

        StreamSource source = null;
        if(data != null ) source = make_stream_source(data.toString());
        Source result = dispatch.invoke(source);
        display_result(result, uri);
    }

    private void display_result(Source result, String uri) {
        DOMResult dom_result = new DOMResult();
        try{
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(result, dom_result);
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            xp.setNamespaceContext(new NSResolver("fib", uri));
            String result_string = xp.evaluate("/fib:response", dom_result.getNode());
            System.out.println(result_string);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private StreamSource make_stream_source(String s) {
        ByteArrayInputStream stream = new ByteArrayInputStream(s.getBytes());
        return new StreamSource(stream);
    }
}
