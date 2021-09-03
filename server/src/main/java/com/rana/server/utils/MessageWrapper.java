package com.rana.server.utils;

import com.rana.server.entity.Message;

import java.nio.channels.SocketChannel;

public class MessageWrapper {

    private Message message;
    private SocketChannel channel;

    public MessageWrapper(Message message, SocketChannel channel) {
        this.message = message;
        this.channel = channel;
    }

    public Message getMessage() {
        return message;
    }

    public SocketChannel getChannel() {
        return channel;
    }
}
