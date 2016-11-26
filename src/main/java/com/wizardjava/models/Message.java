package com.wizardjava.models;

public class Message {

    private String message;
    private Type type;

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public Message(String message) {
        this(message, Type.INFO);
    }

    public Message() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        DANGER,
        INFO
    }
}
