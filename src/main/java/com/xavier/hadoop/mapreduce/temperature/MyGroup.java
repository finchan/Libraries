package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroup extends WritableComparator {
    public MyGroup() {
        super(MyKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyKey myKey1 = (MyKey) a;
        MyKey myKey2 = (MyKey) b;
        int r1 = Integer.compare(myKey1.getYear(), myKey2.getYear());
        if( r1 ==0 ) {
            return Integer.compare(myKey1.getMonth(), myKey2.getMonth());
        }
        return r1;
    }
}
