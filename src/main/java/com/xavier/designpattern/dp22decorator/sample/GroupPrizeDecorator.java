package com.xavier.designpattern.dp22decorator.sample;

import java.util.Date;

/**
 * Created by Xavier on 2018-02-19.
 */
public class GroupPrizeDecorator extends Decorator {
    public GroupPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double money = super.calcPrize(user, begin, end);
        double group = 0.0;
        for(double d: TempDB.mapMonthSaleMoney.values())
            group += d;
        double prize = group* 0.1;
        System.out.println(user + " current month team business bonus: " + prize);
        return money + prize;
    }
}
