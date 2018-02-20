package com.xavier.designpattern.dp22decorator.simulateaop;

/**
 * Created by Xavier on 2018-02-21.
 */
public class CheckDecorator extends Decorator {
    public CheckDecorator(GoodSaleEbi ebi) {
        super(ebi);
    }

    public boolean sale(String user, String customer, SaleModel saleModel) {
        if(!"Xavier".equals(user)) {
            System.out.println("Sorry, user " + user + " doesn't have such privillage!");
            return false;
        }
        return this.ebi.sale(user, customer, saleModel);
    }
}
