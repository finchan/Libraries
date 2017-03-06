package com.xavier.rest.jersey.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Xavier on 2017-02-22.
 */
//JAXB
@XmlRootElement(name="device")
public class Device {
    private String deviceIp;
    private int deviceStatus;

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    //JAXB
    @XmlAttribute
    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Device( ){

    }

    public Device(String deviceIp) {
        super( );
        this.deviceIp = deviceIp;
    }
}
