package com.xavier.syntax.core.interf;

/**
 * Created by Xavier on 2016-07-26.
 */
public interface Moveable {
    Integer INFO = 0;
    Integer DEBUG = 1;
    Integer WARNING = 2;
    Integer ERROR = 3;
    void move(double x, double y);
}
