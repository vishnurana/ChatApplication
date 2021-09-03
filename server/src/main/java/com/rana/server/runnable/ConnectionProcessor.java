package com.rana.server.runnable;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;

public class ConnectionProcessor implements Runnable {

    private Selector selector;
    private BlockingQueue<SocketChannel> queue;

    public ConnectionProcessor() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Selector getSelector() {
        return selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SocketChannel socketChannel = queue.take();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
