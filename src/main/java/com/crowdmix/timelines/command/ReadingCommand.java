package com.crowdmix.timelines.command;

import static com.crowdmix.timelines.command.CommandType.READING;

/**
 * Encapsulates attributes of READING command.
 * 
 * @author andreypereverzin
 */
public class ReadingCommand extends Command {
    public ReadingCommand(String username) {
        super(username);
    }
    
    public CommandType getCommandType() {
        return READING;
    }
}
