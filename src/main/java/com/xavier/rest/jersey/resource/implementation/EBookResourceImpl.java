package com.xavier.rest.jersey.resource.implementation;

import com.xavier.rest.jersey.resource.interf.BookResourceInterface;

/**
 * Created by Xavier on 2017/3/27.
 */
public class EBookResourceImpl implements BookResourceInterface {
    public String getWeight() {
        return "150M";
    }
}
