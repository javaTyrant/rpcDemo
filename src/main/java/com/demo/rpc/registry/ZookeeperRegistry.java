package com.demo.rpc.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.util.List;
import java.util.stream.Collectors;

public class ZookeeperRegistry<T> implements Registry<T> {

    //private final Map<String, List<ServiceInstanceListener<T>>> listeners = Maps.newConcurrentMap();

    private final InstanceSerializer serializer = new JsonInstanceSerializer<>(ServerInfo.class);

    //
    private ServiceDiscovery<T> serviceDiscovery;
    //
    private ServiceCache<T> serviceCache;

    private static final String address = "localhost:2181";

    @SuppressWarnings("unchecked")
    public void start() throws Exception {
        //路径.
        String root = "/demo/rpc";
        // 初始化CuratorFramework
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));
        // 启动Curator客户端
        client.start();
        // client.createContainers(root);

        // 初始化ServiceDiscovery
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServerInfo.class)
                .client(client)
                .basePath(root)
                .serializer(serializer)
                .build();
        //创建ServiceCache，监Zookeeper相应节点的变化，也方便后续的读取
        serviceCache = serviceDiscovery.serviceCacheBuilder()
                .name("/demoService")
                .build();
        //可以添加监听点.
        //serviceCache.addListener();

        // 阻塞当前线程，等待连接成功
        client.blockUntilConnected();
        // 启动ServiceDiscovery
        serviceDiscovery.start();
        // 启动ServiceCache
        serviceCache.start();
    }

    @Override
    public void registerService(ServiceInstance<T> service) throws Exception {
        serviceDiscovery.registerService(service);
    }

    @Override
    public void unregisterService(ServiceInstance<T> service) throws Exception {
        serviceDiscovery.unregisterService(service);
    }

    @Override
    public List<ServiceInstance<T>> queryForInstances(String name) {
        // 直接根据name进行过滤ServiceCache中的缓存数据
        return serviceCache.getInstances()
                .stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toList());
    }
}
