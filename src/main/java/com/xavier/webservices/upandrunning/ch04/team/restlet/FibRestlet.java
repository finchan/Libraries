package com.xavier.webservices.upandrunning.ch04.team.restlet;

import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.*;
import org.restlet.engine.adapter.HttpRequest;

import java.util.*;

/**
 * Created by Xavier on 2018/2/23.
 */
public class FibRestlet {
    private Map<Integer, Integer> cache =
            Collections.synchronizedMap(new HashMap<Integer, Integer>());
    private final String xml_start = "<fib:response xmlns:fib='urn:fib'>";
    private final String xml_stop = "</fib:response>";

    public static void main(String[] args) throws Exception {
        new FibRestlet().publish_service();
    }

    private void publish_service() throws Exception {
        //Create a component to deploy as a service
        Component component = new Component();

        //Add an HTTP server to connect clients to the component
        //In this case, the Simple HTTP engine is the server
        component.getServers().add(Protocol.HTTP, 7777);

        //Attach a handler to handle client requests.
        Restlet handler = new Restlet(component.getContext()) {
            public void handle(Request req, Response res){
                Method http_verb = req.getMethod();

                if(http_verb.equals(Method.GET)){
                    String xml = to_xml();
                    res.setStatus(Status.SUCCESS_OK);
                    res.setEntity(xml, MediaType.APPLICATION_XML);
                } else if (http_verb.equals(Method.POST)) {
                    String nums = ((HttpRequest) req).getHeaders().getFirstValue("nums");
                    if(nums != null) {
                        nums = nums.replace('[', '\0');
                        nums = nums.replace(']', '\0');
                        String[ ] parts = nums.split(",");
                        List<Integer> list = new ArrayList<Integer>();
                        for (String next : parts) {
                            int n = Integer.parseInt(next.trim());
                            cache.put(n, countRabbits(n));
                            list.add(cache.get(n));
                        }
                        String xml =
                                xml_start + "POSTed: " + list.toString() + xml_stop;
                        res.setStatus(Status.SUCCESS_OK);
                        res.setEntity(xml, MediaType.APPLICATION_XML);
                    }
                } else if (http_verb.equals(Method.DELETE)) {
                    cache.clear(); // remove the resource
                    String xml =
                            xml_start + "Resource deleted" + xml_stop;
                    res.setStatus(Status.SUCCESS_OK);
                    res.setEntity(xml, MediaType.APPLICATION_XML);
                } else // only GET, POST, and DELETE supported
                    res.setStatus(Status.SERVER_ERROR_NOT_IMPLEMENTED);
            }
        };
        // Publish the component as a service and start the service.
        System.out.println("FibRestlet at: http://localhost:7777/fib");
        component.getDefaultHost().attach("/fib", handler);
        component.start();
    }

    private int countRabbits(int n) {
        n = Math.abs(n); // eliminate possibility of a negative argument
        // Easy cases.
        if (n < 2) {
            return n;
        }
        // Return cached values if present.
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (cache.containsKey(n - 1) &&
                cache.containsKey(n - 2)) {
            cache.put(n, cache.get(n - 1) + cache.get(n - 2));
            return cache.get(n);
        }
        // Otherwise, compute from scratch, cache, and return.
        int fib = 1, prev = 0;
        for (int i = 2; i <= n; i++) {
            int temp = fib;
            fib += prev;
            prev = temp;
        }
        cache.put(n, fib);
        return fib;
    }

    private String to_xml() {
        Collection<Integer> list = cache.values();
        return xml_start + "GET: " + list.toString() + xml_stop;
    }
}
