package com.xavier.designpattern.dp22decorator.sample;

import java.util.Date;

/**
 * Created by Xavier on 2018-02-19.
 */
public abstract class Decorator extends Component{
    protected Component c;

    public Decorator(Component c) {
        this.c = c;
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        return c.calcPrize(user, begin, end);
    }
}
