package com.xavier.hadoop.mapreduce.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String [] strArray = value.toString().split(",");
        String strContent = "";
        for(int i=0; i<strArray.length; i++) {
            if(i ==3) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(Long.parseLong(strArray[i].trim()+"000"));
                str = " " + str;
                strContent = strContent + str + ",";
            } else {
                strContent = strContent + strArray[i] + ",";
            }
        }
        context.write(new Text(strContent), NullWritable.get());
    }
}
