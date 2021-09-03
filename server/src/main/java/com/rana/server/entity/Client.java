package com.rana.server.entity;

import java.nio.channels.SocketChannel;

public class Client extends BasicEntity{

    private SocketChannel socketChannel;

    public Client(long id, String name, SocketChannel socket) {
        super(id, name);
        this.socketChannel = socket;
    }

    public SocketChannel getSocket() {
        return socketChannel;
    }

}
