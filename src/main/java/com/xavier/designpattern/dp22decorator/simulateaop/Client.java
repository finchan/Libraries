package com.xavier.designpattern.dp22decorator.simulateaop;

/**
 * Created by Xavier on 2018-02-21.
 */
public class Client {
    public static void main(String[] args) {
        GoodSaleEbi ebi = new CheckDecorator( new LogDecorator( new GoodSaleEbo()));
        SaleModel sm =  new SaleModel();
        sm.setGoods("Cellphone");
        sm.setSaleNum(1000);
        ebi.sale("Javier", "Gacia", sm);
        ebi.sale("Xavier", "Nate", sm);
    }
}
