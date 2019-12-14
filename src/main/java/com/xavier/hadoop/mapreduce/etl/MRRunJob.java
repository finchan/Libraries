package com.xavier.hadoop.mapreduce.etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MRRunJob {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "D:\\Development\\hadoop-common-2.2.0-bin-master");
        System.load("D:\\Development\\hadoop-common-2.2.0-bin-master\\bin\\hadoop.dll");
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.153.132:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        Job job = null;
        FileSystem fs = null;

        try {
            fs = FileSystem.get(conf);
            job = Job.getInstance(conf, "mywc");
            job.setJarByClass(MRRunJob.class);
            job.setMapperClass(ETLMapper.class);
            job.setReducerClass(ETLReducer.class);
            //Map output key
            job.setOutputKeyClass(Text.class);
            //Map output value
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.addInputPath(job, new Path("/hdfsfiles/mapreduce/etl/input/"));
            Path path = new Path("/hdfsfiles/mapreduce/etl/output/");

            if(fs.exists(path)) {
                fs.delete(path, true);
            }

            FileOutputFormat.setOutputPath(job, path);
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
