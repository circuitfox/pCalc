package com.example.chris.pcalc.input;

public class Message {
    private MessageType type;
    private String value;

    public Message(MessageType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Get the type of this message.
     * @return the type of this message
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Get the value of this message.
     * @return the value contained in this message
     */
    public String getValue() {
        return value;
    }

    public String toString() {
        return type.toString() + "(" + value + ")";
    }
}
