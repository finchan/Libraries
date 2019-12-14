package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<MyKey, Text, NullWritable, Text> {
    @Override
    protected void reduce(MyKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(Text t: values) {
            sum++;
            if(sum > 3) {
                break;
            } else {
                context.write(NullWritable.get(), t);
            }
        }
    }
}
