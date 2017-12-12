
package com.xavier.webservices.upandrunning.ch02.ts.docclient;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for getTimeAsEclapsedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTimeAsEclapsedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="getTimeAsEclapsedResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTimeAsEclapsedResponse", propOrder = {
    "_return"
})
public class GetTimeAsEclapsedResponse {

    @XmlElement(name = "return")
    protected long _return;

    /**
     * Gets the value of the return property.
     * 
     */
    public long getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     */
    public void setReturn(long value) {
        this._return = value;
    }

}
