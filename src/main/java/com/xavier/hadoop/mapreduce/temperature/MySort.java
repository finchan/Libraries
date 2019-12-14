package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MySort extends WritableComparator {
    public MySort() {
        super(MyKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyKey myKey1 = (MyKey) a;
        MyKey myKey2 = (MyKey) b;
        int r1 = Integer.compare(myKey1.getYear(), myKey2.getYear());
        if(r1 == 0) {
            int r2 = Integer.compare(myKey1.getMonth(), myKey2.getMonth());
            if(r2 == 0) {
                return -Double.compare(myKey1.getAir(), myKey2.getAir());
            }
            return r2;
        }
        return r1;
    }
}
