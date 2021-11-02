package com.demo.rpc.registry;

import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;

public interface Registry<T> {

    /**
     * 注册服务
     *
     * @param service 服务实例
     * @throws Exception 异常
     */
    void registerService(ServiceInstance<T> service) throws Exception;

    /**
     * 取消注册
     *
     * @param service service
     * @throws Exception 异常
     */
    void unregisterService(ServiceInstance<T> service) throws Exception;

    /**
     * 根据name查询实例
     *
     * @param name name
     * @return list
     * @throws Exception 异常
     */
    List<ServiceInstance<T>> queryForInstances(String name) throws Exception;
}
