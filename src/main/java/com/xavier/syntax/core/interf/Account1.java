package com.xavier.syntax.core.interf;

/**
 * Created by Xavier on 2016/7/26.
 */
public class Account1 implements Cloneable{
    User1 user;
    long balance;

    @Override
    public Account1 clone( ) throws CloneNotSupportedException {
        Account1 cloned =  (Account1) super.clone( );
        cloned.user = (User1) user.clone();
        return cloned;
    }
}
