package com.xavier.designpattern.dp22decorator.simulateaop;

/**
 * Created by Xavier on 2018-02-21.
 */
public class GoodSaleEbo implements  GoodSaleEbi {
    public boolean sale(String user, String customer, SaleModel saleModel) {
        System.out.println(user + " saves. " + customer + " purchased " + saleModel + ".");
        return true;
    }
}
