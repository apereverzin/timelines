package com.crowdmix.timelines.command;

/**
 * Abstract superclass for all commands.
 * 
 * @author andreypereverzin
 */
public abstract class Command {
    private final String username;

    public Command(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public abstract CommandType getCommandType();
}
