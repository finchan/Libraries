package com.xavier.webservices.upandrunning.ch03.mctx;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Map;
import java.util.Set;

/**
 * Created by Xavier on 2017/12/19.
 */
public class EchoHandler implements SOAPHandler<SOAPMessageContext> {
    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isInboundMessage = !(Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(isInboundMessage) {
            System.out.println("<-------------------------------");
            MapDump.dump_map((Map) context, "");
            System.out.println("------------------------------->");
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {

    }
}
