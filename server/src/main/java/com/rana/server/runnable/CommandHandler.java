package com.rana.server.runnable;

import com.rana.server.entity.Client;
import com.rana.server.entity.Message;
import com.rana.server.repository.ClientRepository;
import com.rana.server.utils.MessageWrapper;

import java.util.concurrent.BlockingQueue;

public class CommandHandler implements Runnable {

    private ClientRepository repository;

    private BlockingQueue<MessageWrapper> newMessageQueue;
    private BlockingQueue<Message> outMessageQueue;

    public static final String CMD_CONNECTED = "connected";
    public static final String CMD_GROUP = "group";
    public static final String CMD_CHAT = "chat";
    public static final String CMD_MESSAGE = "message";

    public CommandHandler(BlockingQueue<MessageWrapper> newMessageQueue, BlockingQueue<Message> outMessageQueue, ClientRepository repository) {
        this.newMessageQueue = newMessageQueue;
        this.outMessageQueue = outMessageQueue;
    }

    @Override
    public void run() {

        try {
            MessageWrapper newMessage = newMessageQueue.take();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void handleMessage(MessageWrapper messageWrapper) {
        switch (messageWrapper.getMessage().getCommand()) {
            case CMD_CONNECTED:
                createClient(messageWrapper);
                break;
            case CMD_MESSAGE:
                sendMessage(messageWrapper);
                break;
            case CMD_CHAT:
                oneToOneChat(messageWrapper);
                break;
        }
    }

    private void createClient(MessageWrapper messageWrapper) {
        if (!repository.contains(messageWrapper.getMessage().getFrom())) {
            Client client = new Client(System.currentTimeMillis(), messageWrapper.getMessage().getMessage(), messageWrapper.getChannel());
            repository.add(client);

        }
    }

    private void oneToOneChat(MessageWrapper messageWrapper) {
        if (repository.contains(messageWrapper.getMessage().getTo())) {
            // Todo send message to client
        } else {
            // Todo send sender a USER_NOT_FOUND
        }
    }

    private void sendMessage(MessageWrapper messageWrapper) {

    }
}
