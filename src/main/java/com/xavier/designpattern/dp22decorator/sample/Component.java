package com.xavier.designpattern.dp22decorator.sample;


import java.util.Date;

/**
 * Created by Xavier on 2018-02-19.
 */
public abstract class Component {
    public abstract double calcPrize(String user, Date begin, Date end );
}
