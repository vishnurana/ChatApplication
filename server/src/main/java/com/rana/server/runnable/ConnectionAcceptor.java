package com.rana.server.runnable;

import com.rana.server.config.ApplicationProperties;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;

public class ConnectionAcceptor implements Runnable {

    private ServerSocketChannel serverChannel;
    private int port;

    private BlockingQueue<SocketChannel> queue;


    public ConnectionAcceptor() {
        try {
            port = ApplicationProperties.getInstance().getIntProperty("server.port");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start(int port) throws IOException {
        serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
    }

    @Override
    public void run() {
        try {
            start(port);
            while (true) {
                SocketChannel clientChannel = serverChannel.accept();
                queue.put(clientChannel);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
