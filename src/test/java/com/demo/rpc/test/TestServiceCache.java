package com.demo.rpc.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

/**
 * @author lufengxiang
 * @since 2021/11/2
 **/
public class TestServiceCache {
    //cache是一种缓存机制，可以借助cache实现监听。
    //简单来说，cache在客户端缓存了znode的各种状态，当感知到zk集群的znode状态变化，会触发event事件，注册的监听器会处理这些事件。是不是很简单。

    static class MyListener implements ServiceCacheListener {

        @Override
        public void cacheChanged() {
            System.out.println("hello");
        }

        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

        }
    }
}
