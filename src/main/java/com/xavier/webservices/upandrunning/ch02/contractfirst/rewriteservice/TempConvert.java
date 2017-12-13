
package com.xavier.webservices.upandrunning.ch02.contractfirst.rewriteservice;

import com.xavier.webservices.upandrunning.ch02.contractfirst.generatedclient.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 1. Copy ServiceSoap (generated from wsdl) interface
 * 2. Change Interface to Class,
 * 3. Modify service name...
 * 4. Change SIE name if you like
 * 5. className in @RequestWrapper and @ResponseWrapper is very important！！！！！！
 */
@WebService(name = "TempConvert", targetNamespace = "http://tempConvertURI.org/")
@XmlSeeAlso( {
        ObjectFactory.class
})
public class TempConvert {


    /**
     * @param t
     * @return returns double
     */
    @WebMethod(operationName = "c2f", action = "http://tempConvertURI.org/c2f")
    @WebResult(name = "c2fResult", targetNamespace = "http://tempConvertURI.org/")
    @RequestWrapper(localName = "c2f", targetNamespace = "http://tempConvertURI.org/", className = "com.xavier.webservices.upandrunning.ch02.contractfirst.generatedclient.C2F")
    @ResponseWrapper(localName = "c2fResponse", targetNamespace = "http://tempConvertURI.org/", className = "com.xavier.webservices.upandrunning.ch02.contractfirst.generatedclient.C2FResponse")
    public double c2F(
            @WebParam(name = "t", targetNamespace = "http://tempConvertURI.org/")
                    double t) {
        return 32.0 + (t * 9.0 / 5.0);
    }

    /**
     * @param t
     * @return returns double
     */
    @WebMethod(operationName = "f2c", action = "http://tempConvertURI.org/f2c")
    @WebResult(name = "f2cResult", targetNamespace = "http://tempConvertURI.org/")
    @RequestWrapper(localName = "f2c", targetNamespace = "http://tempConvertURI.org/", className = "com.xavier.webservices.upandrunning.ch02.contractfirst.generatedclient.F2C")
    @ResponseWrapper(localName = "f2cResponse", targetNamespace = "http://tempConvertURI.org/", className = "com.xavier.webservices.upandrunning.ch02.contractfirst.generatedclient.F2CResponse")
    public double f2C(
            @WebParam(name = "t", targetNamespace = "http://tempConvertURI.org/")
                    double t){
        return (5.0/9.0)*(t-32.0);
    }

}
