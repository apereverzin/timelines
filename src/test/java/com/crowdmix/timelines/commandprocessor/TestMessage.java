package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.domain.Message;

/**
 * Extends Message class for testing purposes.
 * 
 * @author andreypereverzin
 */
public class TestMessage extends Message {

    TestMessage(String text, long timestamp) {
        super(text);
        this.timestamp = timestamp;
    }
}
