package com.xavier.hadoop.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZooKeeperDemo implements Watcher {
    private static final int SESSION_TIMEOUT = 30000;
    public static ZooKeeper zooKeeper;
    public static void main(String[] args) {
        String path = "/mynode";
        try{
            zooKeeper = new ZooKeeper("192.168.153.132:2181", SESSION_TIMEOUT, new ZooKeeperDemo());
            //exists - Return the stat of the node of the given path. Return null if no such a node exists.
            //If the watch is true and the call is successful (no exception is thrown),
            // a watch will be left on the node with the given path. The watch will be triggered
            // by a successful operation that creates/delete the node or sets the data on the node.
            Stat stat = zooKeeper.exists(path, true);
            if (stat == null) {
                zooKeeper.create(path, "myContent1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                Thread.sleep(3000);
            }
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
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if(Event.EventType.NodeCreated == watchedEvent.getType()) {
                System.out.println("Node created successfully");
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
