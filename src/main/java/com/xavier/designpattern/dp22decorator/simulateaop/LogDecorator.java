package com.xavier.designpattern.dp22decorator.simulateaop;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xavier on 2018-02-21.
 */
public class LogDecorator extends Decorator {
    public LogDecorator(GoodSaleEbi ebi) {
        super(ebi);
    }

    public boolean sale(String user, String customer, SaleModel saleModel) {
        boolean f = this.ebi.sale(user, customer, saleModel);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("Log: " + user + " saves a record at " + df.format(new Date())
                + ". Customer is " + customer + " and product info is: " + saleModel);
        return f;
    }
}
