package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper <Text, Text, MyKey, Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String [] strArray = key.toString().split("-");
        MyKey myKey = new MyKey();
        myKey.setYear(Integer.parseInt(strArray[0]));
        myKey.setMonth(Integer.parseInt(strArray[1]));
        myKey.setAir(Double.parseDouble(value.toString()));
        context.write(myKey, new Text(key + "\t" + value));
    }
}
