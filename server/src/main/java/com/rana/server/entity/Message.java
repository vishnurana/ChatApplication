package com.rana.server.entity;

import java.util.Optional;

public class Message {

    private String command;
    private long from;
    private long to;
    private boolean isGroupChat;
    private String message;

    public Message(String message) {
        if (message == null) {
            return;
        }
        String[] messageInfo = message.split("|");
        Optional.ofNullable(messageInfo).filter(info -> info.length == 4).ifPresent(info -> {
            command = info[0];
            from = Long.parseLong(info[1]);
            to = Long.parseLong(info[2]);
            isGroupChat = Boolean.getBoolean(info[3]);
            Message.this.message = info[4];
        });
    }

    public Message(String command, long from, long to, boolean isGroupChat, String message) {
        this.command = command;
        this.from = from;
        this.to = to;
        this.isGroupChat = isGroupChat;
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s", command, from, to, isGroupChat, message);
    }
}
