package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner <MyKey, Text> {
    @Override
    public int getPartition(MyKey myKey, Text value, int numPartitions) {
        return (myKey.getYear()-1949) % numPartitions;
    }
}
