package com.xavier.webservices.upandrunning.ch03.fibcsoap12;

import com.xavier.webservices.upandrunning.ch03.fibcsoap12.FibException_Exception;
import com.xavier.webservices.upandrunning.ch03.fibcsoap12.RabbitCounterSOAP12;
import com.xavier.webservices.upandrunning.ch03.fibcsoap12.RabbitCounterSOAP12Service;

/**
 * Created by Xavier on 2017/12/17.
 */
public class FibClientSOAP12 {
    public static void main(String args[]){
        RabbitCounterSOAP12Service service = new RabbitCounterSOAP12Service();
        RabbitCounterSOAP12 port = service.getRabbitCounterSOAP12Port();
        try {
            int n = -45;
            System.out.print("fib(" + n + ") = " + port.countRabbits(n));
        } catch (FibException_Exception e) {
            e.printStackTrace();
        }
    }
}
