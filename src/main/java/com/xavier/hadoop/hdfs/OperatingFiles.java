package com.xavier.hadoop.hdfs;
import java.io.FileNotFoundException;
import	java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/*
    If you are using Windows to run a Java file to manipulate HDFS,
    you have to set hadoop.home.dir to hadoop-common-2.2.0-bin-master.
    As well, you have to use UGI.
    For Hadoop 2.7.7, JDK 7 is suggested!
 */
public class OperatingFiles {
    static Configuration conf;
    static FileSystem hdfs;
    static {
        System.setProperty("hadoop.home.dir", "D:\\Development\\hadoop-common-2.2.0-bin-master");
        System.load("D:\\Development\\hadoop-common-2.2.0-bin-master\\bin\\hadoop.dll");
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.153.132:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            hdfs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        //Create a HDFS folders
//        new OperatingFiles().createDir();
        //Upload a file from local system to HDFS folder
//        new OperatingFiles().uploadFile();
        //Rename a File
//        new OperatingFiles().renameFile();
        //List all files
        new OperatingFiles().listFiles(new Path("/"));
        //Download a file
//        new OperatingFiles().downloadFile();
    }

    public void createDir() throws IOException{
        String dir = "/hdfsfiles/javaFolder";
        Path path = new Path(dir);
        if(hdfs.exists(path)) {
            System.out.println("Dir \t" + conf.get("fs.default.name") + dir
                                            + "\t already exists.");
            return;
        }
        hdfs.mkdirs(path);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }

    public void uploadFile() throws IOException{
        Path src = new Path("E:\\ski.wsdl");
        Path dest = new Path("/hdfsfiles/javaFolder/ski.wsdl");
        hdfs.copyFromLocalFile(src, dest);
    }

    public void downloadFile() throws IOException {
        Path src = new Path("/hdfsfiles/javaFolder/ski.wsdl");
        Path desc = new Path("E:\\ski.wsdl");
        hdfs.copyToLocalFile(src, desc);
    }

    public void renameFile() throws IOException {
        Path oldFile = new Path("/hdfsfiles/javaFolder/ski.wsdl");
        Path newFile = new Path("/hdfsfiles/javaFolder/ski2.wsdl");
        if(hdfs.exists(oldFile)) {
            hdfs.rename(oldFile, newFile);
        }
    }

    public void listFiles(Path path) {
        try{
            FileStatus[] fileStatusArray = hdfs.listStatus(path);
            for(int i=0; i < fileStatusArray.length; i++) {
                FileStatus status = fileStatusArray[i];
                if(status.isDirectory()) {
                    System.out.println("Current Path is a directory: " + status.getPath());
                    listFiles(status.getPath());
                } else {
                    System.out.println("Current Path is: " + status.getPath());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
