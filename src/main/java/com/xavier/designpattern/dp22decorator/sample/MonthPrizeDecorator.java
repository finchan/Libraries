package com.xavier.designpattern.dp22decorator.sample;

import java.util.Date;

/**
 * Created by Xavier on 2018-02-19.
 */
public class MonthPrizeDecorator extends Decorator {
    public MonthPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double money = super.calcPrize(user, begin, end);
        double prize =TempDB.mapMonthSaleMoney.get(user) * 0.3;
        System.out.println(user + " current month business bonus: " + prize);
        return money + prize;
    }
}
