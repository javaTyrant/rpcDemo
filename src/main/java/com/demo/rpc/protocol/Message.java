package com.demo.rpc.protocol;

@SuppressWarnings("unused")
public class Message<T> {
    //头信息
    private Header header;
    //泛型.
    private T content;


    public Message(Header header, T content) {
        this.content = content;
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
