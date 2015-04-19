package com.crowdmix.timelines.commandprocessor;

/**
 * Exception throws when command cannot be parsed.
 * 
 * @author andreypereverzin
 */
@SuppressWarnings("serial")
public class CommandParseException extends Exception {
    public CommandParseException(String msg) {
        super(msg);
    }
}
