package com.xavier.webservices.upandrunning.ch02.tsa;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * Created by Xavier on 2017/12/14.
 */
@WebService(name =                           "AnnotatedTimeServer",
                        serviceName =               "RevisedTimeServer",
                        targetNamespace =       "http://ch02.tsa")
@SOAPBinding(style=                           SOAPBinding.Style.DOCUMENT,
                            use =                           SOAPBinding.Use.LITERAL,
                            parameterStyle =        SOAPBinding.ParameterStyle.WRAPPED)
public class TimerServer {
    @WebMethod (operationName = "time_string")
    @WebResult( name="ts_out",
                            targetNamespace = "http://ch02.tsa")
    public String getTimeAsString(
            @WebParam(name="client_message",
                    targetNamespace = "http://ch02.tsa",
                    mode = WebParam.Mode.IN)
            String msg) {
        return msg + " at " + new Date().toString();
    }

    @WebMethod (operationName = "time_elapsed")
    public long getTimeAsElapsed() {
        return new Date().getTime();
    }

    @WebMethod
    @Oneway
    public void acceptInput(String msg) {
        System.out.println(msg);
    }
}
