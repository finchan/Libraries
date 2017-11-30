package com.xavier.webservices.upandrunning.ch01.ts;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Created by Xavier on 2017/11/30.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface TimeServer { //SEI
    @WebMethod
    String getTimeAsString( );
    @WebMethod
    long getTimeAsEclapsed( );
}
