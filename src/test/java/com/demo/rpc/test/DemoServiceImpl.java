package com.demo.rpc.test;

public class DemoServiceImpl implements DemoService {

    /**
     *
     */
    @Override
    public String sayHello(String param) {
        System.out.println("param:" + param);
        return "hello:" + param;
    }

}