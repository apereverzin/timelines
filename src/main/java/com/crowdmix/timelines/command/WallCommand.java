package com.crowdmix.timelines.command;

import static com.crowdmix.timelines.command.CommandType.WALL;

/**
 * Encapsulates attributes of WALL command.
 * 
 * @author andreypereverzin
 */
public class WallCommand extends Command {
    public WallCommand(String username) {
        super(username);
    }
    
    public CommandType getCommandType() {
        return WALL;
    }
}
