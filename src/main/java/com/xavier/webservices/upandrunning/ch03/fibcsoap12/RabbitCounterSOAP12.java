
package com.xavier.webservices.upandrunning.ch03.fibcsoap12;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RabbitCounterSOAP12", targetNamespace = "http://ch03.fib")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RabbitCounterSOAP12 {


    /**
     * 
     * @param arg0
     * @return
     *     returns int
     * @throws FibException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "countRabbits", targetNamespace = "http://ch03.fib", className = "com.xavier.webservices.upandrunning.ch03.fibcsoap12.CountRabbits")
    @ResponseWrapper(localName = "countRabbitsResponse", targetNamespace = "http://ch03.fib", className = "com.xavier.webservices.upandrunning.ch03.fibcsoap12.CountRabbitsResponse")
    @Action(input = "http://ch03.fib/RabbitCounterSOAP12/countRabbitsRequest", output = "http://ch03.fib/RabbitCounterSOAP12/countRabbitsResponse", fault = {
        @FaultAction(className = FibException_Exception.class, value = "http://ch03.fib/RabbitCounterSOAP12/countRabbits/Fault/FibException")
    })
    public int countRabbits(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0)
        throws FibException_Exception
    ;

}
