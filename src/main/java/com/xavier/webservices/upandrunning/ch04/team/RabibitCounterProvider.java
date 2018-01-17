package com.xavier.webservices.upandrunning.ch04.team;

import javax.annotation.Resource;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Created by Xavier on 2018/1/17.
 */
@WebServiceProvider
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class RabibitCounterProvider implements Provider<Source> {

    @Resource
    protected WebServiceContext wsc;

    private Map<Integer, Integer> cache = Collections.synchronizedMap(new HashMap<Integer, Integer>());

    private final String xml_start="<fib:response xmlns:fib='urn:fib'>";
    private final String xml_stop = "</fib:response>";
    private final String uri = "urn:fib";

    public Source invoke(Source request) {
        return null;
    }

    private Source doPost(Source request) {
        if (request == null) throw new HTTPException(400);
        String nums = extract_request(request);
        nums = nums.replace('[', '\0');
        nums = nums.replace(']', '\0');
        String [] parts = nums.split(",");
        List<Integer> list = new ArrayList<Integer>();
        for(String next: parts) {
            int n = Integer.parseInt(next.trim());
            cache.put(n, countRabbits(n));
            list.add(cache.get(n));
        }
        String xml = xml_start + "POSTed: " + list.toString() + xml_stop;
        return make_stream_source(xml);
    }

    private Source doGet(Source request) {
        Collection<Integer> list = cache.values();
        String xml = xml_start + "GET: " + list.toString() + xml_stop;
        return make_stream_source(xml);
    }

    private Source doDelete(Source request) {
        cache.clear();
        String xml = xml_start + "DELETE: " + xml_stop;
        return make_stream_source(xml);
    }

    private String extract_request(Source request) {
        String request_string = null;
        try {
            DOMResult dom_result = new DOMResult();
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(request, dom_result);

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            xp.setNamespaceContext(new NSResolver("fib", uri));
            request_string = xp.evaluate("/fib:request", dom_result.getNode());
        } catch (TransformerConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        } catch (XPathExpressionException e) {
            System.err.println(e);
        }
        return request_string;
    }

    private Source make_stream_source(String msg) {
        System.out.println(msg);
        ByteArrayInputStream stream = new ByteArrayInputStream(msg.getBytes());
        return new StreamSource(stream);
    }

    private Integer countRabbits(int n) {
        if (n < 0) throw new HTTPException(403);
        if(n < 2) return n;

        if(cache.containsKey(n)) return cache.get(n);
        if(cache.containsKey(n-1) && cache.containsKey(n-2)) {
            cache.put(n, cache.get(n-1) + cache.get(n-2));
            return cache.get(n);
        }

        int fib = 1, prev = 0;
        for(int i=2; i<=n; i++) {
            int temp = fib;
            fib += prev;
            prev = temp;
        }
        cache.put(n, fib);
        return fib;
    }
}
