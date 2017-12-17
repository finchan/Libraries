
package com.xavier.webservices.upandrunning.ch03.fibc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RabbitCounterService", targetNamespace = "http://ch03.fib", wsdlLocation = "http://localhost:8888/fib?wsdl")
@HandlerChain(file = "handler-chain.xml")
public class RabbitCounterService
    extends Service
{

    private final static URL RABBITCOUNTERSERVICE_WSDL_LOCATION;
    private final static WebServiceException RABBITCOUNTERSERVICE_EXCEPTION;
    private final static QName RABBITCOUNTERSERVICE_QNAME = new QName("http://ch03.fib", "RabbitCounterService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8888/fib?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RABBITCOUNTERSERVICE_WSDL_LOCATION = url;
        RABBITCOUNTERSERVICE_EXCEPTION = e;
    }

    public RabbitCounterService() {
        super(__getWsdlLocation(), RABBITCOUNTERSERVICE_QNAME);
    }

    public RabbitCounterService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RABBITCOUNTERSERVICE_QNAME, features);
    }

    public RabbitCounterService(URL wsdlLocation) {
        super(wsdlLocation, RABBITCOUNTERSERVICE_QNAME);
    }

    public RabbitCounterService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RABBITCOUNTERSERVICE_QNAME, features);
    }

    public RabbitCounterService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RabbitCounterService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RabbitCounter
     */
    @WebEndpoint(name = "RabbitCounterPort")
    public RabbitCounter getRabbitCounterPort() {
        return super.getPort(new QName("http://ch03.fib", "RabbitCounterPort"), RabbitCounter.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RabbitCounter
     */
    @WebEndpoint(name = "RabbitCounterPort")
    public RabbitCounter getRabbitCounterPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ch03.fib", "RabbitCounterPort"), RabbitCounter.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RABBITCOUNTERSERVICE_EXCEPTION!= null) {
            throw RABBITCOUNTERSERVICE_EXCEPTION;
        }
        return RABBITCOUNTERSERVICE_WSDL_LOCATION;
    }

}
