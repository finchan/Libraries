package com.xavier.webservices.upandrunning.ch03.fib;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Xavier on 2017/12/18.
 */
public class UUIDValidator implements SOAPHandler<SOAPMessageContext> {

    private static final boolean trace = true;

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean response_p = !(Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(response_p) {
            try {
                SOAPMessage msg = context.getMessage();
                SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
                SOAPHeader hdr = env.getHeader();

                if(hdr == null)
                    generateSOAPFault(msg, "No message header.");

                Iterator it = hdr.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                if (it == null || !it.hasNext())
                    generateSOAPFault(msg, "No header block for next actor.");
                Node next = (Node) it.next();
                String value = (next == null) ? null : next.getValue();
                if(value == null)
                    generateSOAPFault(msg, "UUID in header block.");

                UUID uuid = UUID.fromString(value.trim());
                if(uuid.variant() != UUIDvariant || uuid.version() != UUIDversion)
                    generateSOAPFault(msg, "Bad UUID variant or verison.");

                if(trace) msg.writeTo(System.out);
            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {

    }

/*
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	<S:Header/>
	<S:Body>
		<S:Fault xmlns:ns4="http://www.w3.org/2003/05/soap-envelope" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xml="http://www.w3.org/XML/1998/namespace">
			<faultcode>S:Server</faultcode>
			<faultstring>UUID in header block.</faultstring>
		</S:Fault>
	</S:Body>
</S:Envelope>
*/

    private void generateSOAPFault(SOAPMessage msg, String reason) {
        try {
            SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault fault = body.addFault();
            fault.setFaultString(reason);
            //SOAPFaultException is a runtime Exception!!!!
            throw new SOAPFaultException(fault);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private static final int UUIDvariant = 2; //layout
    private static final int UUIDversion = 4; //version
}
