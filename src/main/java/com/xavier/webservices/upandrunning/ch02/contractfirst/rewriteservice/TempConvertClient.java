package com.xavier.webservices.upandrunning.ch02.contractfirst.rewriteservice;

import com.xavier.webservices.upandrunning.ch02.contractfirst.rewriteservice.client.TempConvertService;
import com.xavier.webservices.upandrunning.ch02.contractfirst.rewriteservice.client.TempConvert;

/**
 * Created by Xavier on 2017/12/13.
 */
public class TempConvertClient {
    public static void main(String[] args) {
       TempConvert tc = new TempConvertService().getTempConvertPort();
        Object c2fValue = tc.c2F(-39.4);
        Object f2cValue = tc.f2C(-40.1);
        System.out.println(c2fValue);
        System.out.println(f2cValue);
    }
}
