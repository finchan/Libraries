package com.xavier.syntax.core.interf;

/**
 * Created by Xavier on 2016/7/26.
 */
public class Account implements Cloneable{
    User user;
    long balance;

    @Override
    public Object clone( ) throws CloneNotSupportedException {
        return super.clone( );
    }
}
