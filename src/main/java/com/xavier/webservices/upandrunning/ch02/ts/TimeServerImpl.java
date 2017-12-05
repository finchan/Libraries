package com.xavier.webservices.upandrunning.ch02.ts;

import javax.jws.WebService;
import java.util.Date;

/**
 * Created by Xavier on 2017/12/5.
 */
@WebService (endpointInterface = "com.xavier.webservices.upandrunning.ch02.ts.TimeServer")
public class TimeServerImpl implements TimeServer {
    public String getTimeAsString() {
        return new Date().toString();
    }

    public long getTimeAsElapsed() {
        return new Date().getTime();
    }
}
