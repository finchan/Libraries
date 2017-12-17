package com.xavier.webservices.upandrunning.ch03.fib;

/**
 * Created by Xavier on 2017/12/17.
 */
public class FibException extends Exception {
    private String details;
    public FibException(String reason, String details) {
        super(reason);
        this.details = details;
    }

    public String getFaultInfo() {
        return details;
    }
}
