package com.xavier.webservices.upandrunning.ch03.mctxclient;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.*;

/**
 * Created by Xavier on 2017/12/19.
 */
public class EchoClient {
    public static void main(String[] args) {
        EchoService service = new EchoService();
        Echo port = service.getEchoPort();
        Map<String, Object> req_ctx = ((BindingProvider) port).getRequestContext();

        String endpoint = "http://localhost:8888";
        String soapActionURI = "echo";

        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint + "/" + soapActionURI);
        req_ctx.put(BindingProvider.SOAPACTION_URI_PROPERTY, soapActionURI);

        Map<String, List<String>> my_header = new HashMap<String, List<String>>();
        my_header.put("Accept-Encoding", Collections.singletonList("gzip"));
        my_header.put("Greeting", Collections.singletonList("Hello World!"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, my_header);

        dump_map(req_ctx, "");

        System.out.println("\n\nRequest above, response below\n\n");

        String response = port.echo("Have a nice day");
        Map<String, Object> res_ctx = ((BindingProvider) port).getResponseContext();
        dump_map(res_ctx, "");
        Object response_code = res_ctx.get(MessageContext.HTTP_RESPONSE_CODE);
    }

    private static void dump_map (Map map, String indent) {
        Set keys =map.keySet();
        for(Object key: keys) {
            System.out.println(indent + key + " ==> " + map.get(key));
            if (map.get(key)  instanceof  Map) {
                dump_map((Map) map.get(key), indent += "    ");
            }
        }
    }
}
