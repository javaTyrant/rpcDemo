package com.demo.rpc.transport;

import com.demo.rpc.protocol.Message;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;

@SuppressWarnings("unused")
public class NettyResponseFuture<T, R> {
    private long createTime;
    private long timeOut;
    //请求.
    private Message<R> request;
    private Channel channel;
    //返回值.
    private Promise<T> promise;

    public NettyResponseFuture(long createTime, long timeOut, Message<R> request, Channel channel, Promise<T> promise) {
        this.createTime = createTime;
        this.timeOut = timeOut;
        this.request = request;
        this.channel = channel;
        this.promise = promise;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public Message<R> getRequest() {
        return request;
    }

    public void setRequest(Message<R> request) {
        this.request = request;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Promise<T> getPromise() {
        return promise;
    }

    public void setPromise(Promise<T> promise) {
        this.promise = promise;
    }
}
