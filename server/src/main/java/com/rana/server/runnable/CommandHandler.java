package com.rana.server.runnable;

import com.rana.server.entity.Message;

import java.util.concurrent.BlockingQueue;

public class CommandHandler implements Runnable{

    private BlockingQueue<Message> newMessageQueue;
    private BlockingQueue<Message> outMessageQueue;

    public CommandHandler(BlockingQueue<Message> newMessageQueue, BlockingQueue<Message> outMessageQueue) {
        this.newMessageQueue = newMessageQueue;
        this.outMessageQueue = outMessageQueue;
    }

    @Override
    public void run() {

        try {
            Message newMessage = newMessageQueue.take();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
