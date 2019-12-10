package com.xavier.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;

public class MRRunJob {
    public static void main(String [] args) {
        System.setProperty("hadoop.home.dir", "D:\\Development\\hadoop-common-2.2.0-bin-master");
        System.load("D:\\Development\\hadoop-common-2.2.0-bin-master\\bin\\hadoop.dll");
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.153.132:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        Job job = null;

        try {
            job = Job.getInstance(conf, "mywc");
            job.setJarByClass(MRRunJob.class);
            job.setMapperClass(WordCountMapper.class);
            job.setReducerClass(WordCountReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job, new Path("/hdfsfiles/mapreduce/wc/input/data/"));
            FileOutputFormat.setOutputPath(job, new Path("/hdfsfiles/mapreduce/wc/output/data/"));
            boolean f = job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
