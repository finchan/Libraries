package com.xavier.webservices.upandrunning.ch03.mctx;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Map;

/**
 * Created by Xavier on 2017/12/19.
 */
@WebService
@HandlerChain(file="handler-chain.xml")
public class Echo {
    @Resource
    WebServiceContext ws_ctx;

    @WebMethod
    public String echo (String from_client) {
        MessageContext ctx = (SOAPMessageContext)ws_ctx.getMessageContext();
        Map req_header  = (Map) ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        MapDump.dump_map((Map) ctx, "");
        String response = "Echoing your message: " + from_client;
        System.out.println(response);
        return response;
    }
}
