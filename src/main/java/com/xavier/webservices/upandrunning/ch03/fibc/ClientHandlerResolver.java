package com.xavier.webservices.upandrunning.ch03.fibc;


import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier on 2017/12/17.
 */
public class ClientHandlerResolver implements HandlerResolver {
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> hchain  = new ArrayList<Handler>();
        hchain.add(new UUIDHandler());
        hchain.add(new TestHandler());
        return hchain;
    }
}
