package com.xavier.webservices.upandrunning.ch01.ts;

import javax.jws.WebService;
import java.util.Date;

/**
 * Created by Xavier on 2017/11/30.
 */
@WebService(endpointInterface = "com.xavier.webservices.upandrunning.ch01.ts.TimeServer")
public class TimeServerImpl implements TimeServer { //SIB
    public String getTimeAsString() {
        return new Date().toString();
    }

    public long getTimeAsEclapsed() {
        return new Date().getTime();
    }
}
