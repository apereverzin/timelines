package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.command.Command;

/**
 * Defines method for command processing functionality.
 * 
 * @author andreypereverzin
 */
public interface CommandProcessor <T extends Command> {
    String processCommand(T command);
}
