package com.xavier.webservices.upandrunning.ch04.team.client;

import com.xavier.webservices.upandrunning.ch04.team.NSResolver;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.net.URI;
import java.util.Map;

/**
 * Created by Xavier on 2018/2/6.
 */
public class YahooClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("YahooClient <your AppID>");
            return;
        }
        String app_id = "appid=" + args[0];

        URI ns_URI = new URI("urn:yahoo:srch");
        QName serviceName = new QName("yahoo", ns_URI.toString());
        QName portName = new QName("yahoo_port", ns_URI.toString());

        Service s = Service.create(serviceName);
        String qs = app_id + "&type=all&results=10&" +
                "sort=date&language=en&query=quantum mechanics";

        //Endpoint address
        URI address = new URI("http",   //HTTP Scheme
                null,   //user info
                "search.yahooapis.com",     //host
                -1,     //port
                "/WebSearchService/V1/webSearch",    //path
                qs,     //query string
                null);      //fragment
        //Or simple version - --------------
        // String endpoint = base_url + qs;

        //Add the appropriate port
        s.addPort(portName, HTTPBinding.HTTP_BINDING, address.toString());

        Dispatch<Source> d =
                s.createDispatch(portName, Source.class, Service.Mode.PAYLOAD);
        Map<String, Object> request_context = d.getRequestContext();
        request_context.put(MessageContext.HTTP_REQUEST_METHOD, "GET");

        Source result = d.invoke(null);
        DOMResult dom_result = new DOMResult();
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.transform(result, dom_result);
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xp = xpf.newXPath();
        xp.setNamespaceContext(new NSResolver("yn", ns_URI.toString()));
        NodeList resultList = (NodeList)
                xp.evaluate("/srch:ResultSet/srch:Result",
                        dom_result.getNode(),
                        XPathConstants.NODESET);
        int len = resultList.getLength();
        for (int i = 1; i <= len; i++) {
            String title =
                    xp.evaluate("/srch:ResultSet/yn:Result(" + i + ")/srch:Title",
                            dom_result.getNode());
            String click =
                    xp.evaluate("/srch:ResultSet/yn:Result(" + i + ")/srch:ClickUrl",
                            dom_result.getNode());
            System.out.printf("(%d) %s (%s)\n", i, title, click);
        }
    }
}
