package com.xavier.designpattern.dp22decorator.sample;

import java.util.Date;

/**
 * Created by Xavier on 2018-02-19.
 */
public class SumPrizeDecorator extends Decorator {
    public SumPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double money = super.calcPrize(user, begin, end);
        double prize = 1000000*0.001;
        System.out.println(user + " calculated prize: " + prize);
        return money + prize;
    }
}
