package com.rana.server.entity;

import java.util.Optional;

public class Message {

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
            from = Long.parseLong(info[0]);
            to = Long.parseLong(info[1]);
            isGroupChat = Boolean.getBoolean(info[2]);
            Message.this.message = info[3];
        });
    }

    public Message(long from, long to, boolean isGroupChat, String message) {
        this.from = from;
        this.to = to;
        this.isGroupChat = isGroupChat;
        this.message = message;
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
        return String.format("%s|%s|%s|%s", from, to, isGroupChat, message);
    }
}
