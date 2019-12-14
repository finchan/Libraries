package com.xavier.hadoop.mapreduce.etl;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ETLReducer extends Reducer <Text, NullWritable, NullWritable, Text> {
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(NullWritable.get(), key);
    }
}
