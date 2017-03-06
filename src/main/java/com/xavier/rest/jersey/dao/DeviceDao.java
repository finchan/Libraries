package com.xavier.rest.jersey.dao;

import com.xavier.rest.jersey.domain.Device;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Xavier on 2017-02-22.
 */
public class DeviceDao{
    ConcurrentHashMap<String, Device> fakeDB = new ConcurrentHashMap<String, Device>( );
    public DeviceDao( ){
        fakeDB.put("10.11.58.163", new Device("10.11.58.163"));
        fakeDB.put("10.11.58.184", new Device("10.11.58.184"));
    }

    public Device getDevice(String ip) {
        return fakeDB.get(ip);
    }

    public Device updateDevice(Device device) {
        String ip = device.getDeviceIp();
        fakeDB.put(ip, device);
        return fakeDB.get(ip);
    }
}
