package com.xavier.webservices.upandrunning.ch03.fibcsoap12;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Xavier on 2017/12/15.
 */

/*
<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
	<S:Header>
		<uuid xmlns="http://ch03.fib" xmlns:env="http://www.w3.org/2003/05/soap-envelope" env:mustUnderstand="true" env:role="http://schemas.xmlsoap.org/soap/actor/next">7b73a74e-89a3-4dd0-9f37-5cb4ea0bceb3</uuid>
	</S:Header>
	<S:Body>
		<ns2:countRabbits xmlns:ns2="http://ch03.fib">
			<arg0>45</arg0>
		</ns2:countRabbits>
	</S:Body>
</S:Envelope>
 */
public class UUIDHandler implements SOAPHandler<SOAPMessageContext> {
    private static final String LoggerName = "ClientSideLogger";
    private Logger logger;
    private final boolean log_p = true;

    public UUIDHandler() {
        logger = Logger.getLogger(LoggerName);
    }

    public Set<QName> getHeaders() {
        if (log_p) {
            logger.info("getHeaders");
        }
        return null;
    }

    public boolean handleMessage(SOAPMessageContext ctx) {
        if (log_p) {
            logger.info("handlerMessage");
        }
        Boolean request_p = (Boolean)
                ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (request_p) {
            try {
                UUID uuid = UUID.randomUUID();
                SOAPMessage msg = ctx.getMessage();
                SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
                SOAPHeader hdr = env.getHeader();
                if (hdr == null) {
                    hdr = env.addHeader();
                }
                QName qname = new QName("http://ch03.fib", "uuid");
                SOAPHeaderElement helem = hdr.addHeaderElement(qname);
                helem.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                helem.setMustUnderstand(true);
                helem.addTextNode(uuid.toString());
                msg.saveChanges();
                msg.writeTo(System.out);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
        return true; //continue down the chain
    }

    public boolean handleFault(SOAPMessageContext ctx) {
        if (log_p) {
            logger.info("handlerFault");
        }
        try {
            ctx.getMessage().writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; //continue down the chain
    }

    public void close(MessageContext context) {
        if (log_p) {
            logger.info("close");
        }
    }
}
