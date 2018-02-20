package com.xavier.designpattern.dp22decorator.simulateaop;

/**
 * Created by Xavier on 2018-02-21.
 */
public abstract class Decorator implements GoodSaleEbi {
    protected GoodSaleEbi ebi;

    public Decorator(GoodSaleEbi ebi) {
        this.ebi = ebi;
    }

}
