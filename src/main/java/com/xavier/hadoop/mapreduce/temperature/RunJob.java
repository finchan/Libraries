package com.xavier.hadoop.mapreduce.temperature;

import com.xavier.hadoop.mapreduce.wordcount.MRRunJob;
import com.xavier.hadoop.mapreduce.wordcount.WordCountMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class RunJob {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "D:\\Development\\hadoop-common-2.2.0-bin-master");
        System.load("D:\\Development\\hadoop-common-2.2.0-bin-master\\bin\\hadoop.dll");
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.153.132:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "\t");
        FileSystem fs = null;
        Job job = null;

        try {
            fs = FileSystem.get(conf);
            job = Job.getInstance(conf, "weather");
            job.setJarByClass(RunJob.class);
            job.setMapperClass(MyMapper.class);
            job.setReducerClass(MyReducer.class);
            //InputFormat
            //An InputFormat for plain text files. Files are broken into lines.
            // Either line feed or carriage-return are used to signal end of line.
            // Each line is divided into key and value parts by a separator byte.
            // If no such a byte exists, the key will be the entire line and value will be empty.
            // The separator byte can be specified in config file under the attribute name
            // mapreduce.input.keyvaluelinerecordreader.key.value.separator.
            // The default is the tab character ('\t').
            job.setInputFormatClass(KeyValueTextInputFormat.class);

            job.setGroupingComparatorClass(MyGroup.class);
            job.setSortComparatorClass(MySort.class);
            job.setPartitionerClass(MyPartitioner.class);

            job.setNumReduceTasks(3);

            //Map outputkey
            job.setOutputKeyClass(MyKey.class);
            //Map outputvalue
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, new Path("/hdfsfiles/mapreduce/temperature/input/"));
            Path path = new Path("/hdfsfiles/mapreduce/temperature/output/");
            if(fs.exists(path)) {
                fs.delete(path, true);
            }
            FileOutputFormat.setOutputPath(job, path);
            boolean f = job.waitForCompletion(true);
            System.out.println("f: " + f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
