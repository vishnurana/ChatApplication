package com.rana.server.runnable;

import com.rana.server.entity.Message;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;

public class MessageReader extends MessageProcessor {

    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private BlockingQueue<Message> newMessageQueue;

    public MessageReader(BlockingQueue<Message> newMessageQueue) {
        this.newMessageQueue = newMessageQueue;
    }

    @Override
    protected void operation(SocketChannel channel) throws IOException, InterruptedException {
        StringBuffer stringMessage = new StringBuffer();
        int bytes;
        while ((bytes = channel.read(buffer)) > 0) {
            buffer.flip();
            stringMessage.append(buffer.asCharBuffer().array());
            buffer.compact();
        }
        if (bytes == -1) {
            System.out.println("Connection closed");
        }
        String message = stringMessage.toString().trim();
        if (!message.isEmpty()) {
            newMessageQueue.put(new Message(message));
        }
    }
}
