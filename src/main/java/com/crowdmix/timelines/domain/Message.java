package com.crowdmix.timelines.domain;

/**
 * Message posted by user.
 * 
 * @author andreypereverzin
 */
public class Message implements Comparable<Message> {
    private final String text;
    // this attribute is defined as protected for testing purposes
    protected long timestamp;

    public Message(String text) {
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Message m) {
        return new Long(m.getTimestamp()).compareTo(timestamp);
    }
}
