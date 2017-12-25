package com.xavier.webservices.upandrunning.ch04.team;

import java.io.Serializable;

/**
 * Created by Xavier on 2017/12/25.
 */
public class HttpResponse implements Serializable{
    private String resp;

    public String getResponse() {
        return resp;
    }

    public void setResponse(String resp) {
        this.resp = resp;
    }
}
