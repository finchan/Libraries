package com.xavier.designpattern.dp22decorator.simulateaop;

/**
 * Created by Xavier on 2018-02-21.
 */
public class SaleModel {
    private String goods;
    private int saleNum;

    public String toString() {
        return "Product Name = " + goods + ", Sale Amount - " + saleNum;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
