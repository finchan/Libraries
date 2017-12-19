
package com.xavier.webservices.upandrunning.ch03.mctxclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Echo", targetNamespace = "http://mctx.ch03.upandrunning.webservices.xavier.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Echo {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://mctx.ch03.upandrunning.webservices.xavier.com/", className = "com.xavier.webservices.upandrunning.ch03.mctxclient.Echo_Type")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://mctx.ch03.upandrunning.webservices.xavier.com/", className = "com.xavier.webservices.upandrunning.ch03.mctxclient.EchoResponse")
    @Action(input = "http://mctx.ch03.upandrunning.webservices.xavier.com/Echo/echoRequest", output = "http://mctx.ch03.upandrunning.webservices.xavier.com/Echo/echoResponse")
    public String echo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
