package com.rana.server.runnable;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public abstract class MessageProcessor implements Runnable {

    private Selector selector;

    public MessageProcessor() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void operation(SocketChannel channel) throws IOException, InterruptedException;

    public void register(SocketChannel socketChannel, int interest) {
        try {
            socketChannel.register(selector, interest);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int ready = selector.select();
                if (ready <= 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    operation((SocketChannel)selectionKey.channel());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
