package com.xavier.webservices.upandrunning.ch03.fibc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;
import java.util.logging.Logger;

/**
 * Created by Xavier on 2017/12/18.
 */
/*
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	<S:Header>
		<uuid xmlns="http://ch03.fib" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" SOAP-ENV:actor="http://schemas.xmlsoap.org/soap/actor/next">8df678ee-e73a-4a72-b3e0-47f011b5591e</uuid>
	</S:Header>
	<S:Body>
	    <!--payload-->
		<ns2:countRabbits xmlns:ns2="http://ch03.fib">
			<arg0>-45</arg0>
		</ns2:countRabbits>
	</S:Body>
</S:Envelope>
 */
public class ArgHandler implements LogicalHandler<LogicalMessageContext>{
    private static final String LoggerName = "ArgLogger";
    private Logger logger;
    private final boolean log_p = true;

    public ArgHandler() {
        this.logger = Logger.getLogger(LoggerName);
    }

    public boolean handleMessage(LogicalMessageContext context) {
        Boolean outbound_p = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(outbound_p) {
            if (log_p) logger.info("ArgHandler.handleMessage");
            LogicalMessage msg = context.getMessage();
            try {
                //Here is very important - should be the same as Client Object package.
                JAXBContext jaxb_ctx = JAXBContext.newInstance("com.xavier.webservices.upandrunning.ch03.fibc");
                Object payload = msg.getPayload(jaxb_ctx);
                if (payload instanceof JAXBElement) {
                    Object obj = ((JAXBElement) payload).getValue();
                    CountRabbits obj_cr = (CountRabbits) obj;
                    int n = obj_cr.getArg0();
                    if(n < 0) {
                        obj_cr.setArg0(Math.abs(n));
                        ((JAXBElement)payload).setValue(obj_cr);
                        msg.setPayload(payload, jaxb_ctx);
                    }
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean handleFault(LogicalMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {

    }
}
