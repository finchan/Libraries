package com.xavier.webservices.upandrunning.ch03.fibsoap12;

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
/*
<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
	<S:Header/>
	<S:Body>
		<ns2:countRabbits xmlns:ns2="http://ch03.fib" xmlns:S="http://www.w3.org/2003/05/soap-envelope" xmlns:xml="http://www.w3.org/XML/1998/namespace">
			<arg0 xmlns="">45</arg0>
		</ns2:countRabbits>
	</S:Body>
</S:Envelope>

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
