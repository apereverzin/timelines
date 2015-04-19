package com.crowdmix.timelines.command;

import static com.crowdmix.timelines.command.CommandType.POSTING;

/**
 * Encapsulates attributes of POSTING command.
 * 
 * @author andreypereverzin
 */
public class PostingCommand extends Command {
    private final String message;

    public PostingCommand(String username, String message) {
        super(username);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public CommandType getCommandType() {
        return POSTING;
    }
}
