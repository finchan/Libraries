package com.xavier.hadoop.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZooKeeperEntireDemo implements Watcher {
    private static final int SESSION_TIMEOUT = 30000;
    public static ZooKeeper zooKeeper;
    public static void main(String[] args) {
        String path = "/zknode10";
        try {
            zooKeeper = new ZooKeeper("192.168.153.132:2181", SESSION_TIMEOUT, new ZooKeeperEntireDemo());
            //Register
            zooKeeper.exists(path, true);
            //Create ZK Node
            zooKeeper.create(path, "myContent1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(3000);
            //Get ZK Node
            byte[] bytes1 = zooKeeper.getData(path, null, null);
            String result1 = new String(bytes1);
            System.out.println(result1);
            //Set ZK Node Content
            //Set the data for the node of the given path
            // if such a node exists and the given version matches the version of the node
            // (if the given version is -1, it matches any node's versions). Return the stat of the node.
            zooKeeper.setData(path, "testSetData000".getBytes(), -1);
            //Get ZK Node Content
            byte[] bytes2 = zooKeeper.getData(path, null, null);
            String result2 = new String(bytes2);
            System.out.println("result after modified: " + result2);
            Thread.sleep(3000);
            //Delete ZK Node
            zooKeeper.delete(path, -1);
            zooKeeper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()) {
            if(Event.EventType.NodeCreated == watchedEvent.getType()) {
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Node Created");
            } else if (Event.EventType.NodeDeleted == watchedEvent.getType()) {
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Node Deleted");
            } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Node Content Changed");
            }
        }
    }
}
