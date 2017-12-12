
package com.xavier.webservices.upandrunning.ch02.ts.docclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xavier.webservices.upandrunning.ch02.ts.docclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTimeAsStringResponse_QNAME = new QName("http://ts.ch01.upandrunning.webservices.xavier.com/", "getTimeAsStringResponse");
    private final static QName _GetTimeAsEclapsed_QNAME = new QName("http://ts.ch01.upandrunning.webservices.xavier.com/", "getTimeAsEclapsed");
    private final static QName _GetTimeAsEclapsedResponse_QNAME = new QName("http://ts.ch01.upandrunning.webservices.xavier.com/", "getTimeAsEclapsedResponse");
    private final static QName _GetTimeAsString_QNAME = new QName("http://ts.ch01.upandrunning.webservices.xavier.com/", "getTimeAsString");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xavier.webservices.upandrunning.ch02.ts.docclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTimeAsString }
     * 
     */
    public GetTimeAsString createGetTimeAsString() {
        return new GetTimeAsString();
    }

    /**
     * Create an instance of {@link GetTimeAsEclapsedResponse }
     * 
     */
    public GetTimeAsEclapsedResponse createGetTimeAsEclapsedResponse() {
        return new GetTimeAsEclapsedResponse();
    }

    /**
     * Create an instance of {@link GetTimeAsEclapsed }
     * 
     */
    public GetTimeAsEclapsed createGetTimeAsEclapsed() {
        return new GetTimeAsEclapsed();
    }

    /**
     * Create an instance of {@link GetTimeAsStringResponse }
     * 
     */
    public GetTimeAsStringResponse createGetTimeAsStringResponse() {
        return new GetTimeAsStringResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.ch01.upandrunning.webservices.xavier.com/", name = "getTimeAsStringResponse")
    public JAXBElement<GetTimeAsStringResponse> createGetTimeAsStringResponse(GetTimeAsStringResponse value) {
        return new JAXBElement<GetTimeAsStringResponse>(_GetTimeAsStringResponse_QNAME, GetTimeAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeAsEclapsed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.ch01.upandrunning.webservices.xavier.com/", name = "getTimeAsEclapsed")
    public JAXBElement<GetTimeAsEclapsed> createGetTimeAsEclapsed(GetTimeAsEclapsed value) {
        return new JAXBElement<GetTimeAsEclapsed>(_GetTimeAsEclapsed_QNAME, GetTimeAsEclapsed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeAsEclapsedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.ch01.upandrunning.webservices.xavier.com/", name = "getTimeAsEclapsedResponse")
    public JAXBElement<GetTimeAsEclapsedResponse> createGetTimeAsEclapsedResponse(GetTimeAsEclapsedResponse value) {
        return new JAXBElement<GetTimeAsEclapsedResponse>(_GetTimeAsEclapsedResponse_QNAME, GetTimeAsEclapsedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.ch01.upandrunning.webservices.xavier.com/", name = "getTimeAsString")
    public JAXBElement<GetTimeAsString> createGetTimeAsString(GetTimeAsString value) {
        return new JAXBElement<GetTimeAsString>(_GetTimeAsString_QNAME, GetTimeAsString.class, null, value);
    }

}
