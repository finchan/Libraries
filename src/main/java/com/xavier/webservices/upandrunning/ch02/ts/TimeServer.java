package com.xavier.webservices.upandrunning.ch02.ts;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Xavier on 2017/12/5.
 */
@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC) // default DOCUMENT
public interface TimeServer {
    @WebMethod
    @WebResult (partName="time_response")
    String getTimeAsString();

    @WebMethod
    @WebResult (partName="time_rseponse_elapsed")
    long getTimeAsElapsed();
}
