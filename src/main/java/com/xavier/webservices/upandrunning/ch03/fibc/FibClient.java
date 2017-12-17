package com.xavier.webservices.upandrunning.ch03.fibc;

/**
 * Created by Xavier on 2017/12/17.
 */
public class FibClient {
    public static void main(String args[]){
        RabbitCounterService service = new RabbitCounterService();
        RabbitCounter port = service.getRabbitCounterPort();
        try {
            int n = -45;
            System.out.print("fib(" + n + ") = " + port.countRabbits(n));
        } catch (FibException_Exception e) {
            e.printStackTrace();
        }
    }
}
