package com.crowdmix.timelines.commandprocessor;

import java.util.HashMap;
import java.util.Map;

import com.crowdmix.timelines.command.Command;
import com.crowdmix.timelines.command.CommandType;

/**
 * Processes commands entered by the user.
 * 
 * @author andreypereverzin
 */
public class CommandDispatcher {
    private final Map<CommandType, CommandProcessor<? extends Command>> commandProcessors = new HashMap<>();
    
    public void registerCommandProcessor(CommandType commandType, CommandProcessor<? extends Command> commandProcessor) {
        commandProcessors.put(commandType, commandProcessor);
    }
    
    public <T extends Command> String processCommand(T command) {
        @SuppressWarnings("unchecked")
        CommandProcessor<T> commandProcessor = (CommandProcessor<T>)commandProcessors.get(command.getCommandType());
        return commandProcessor.processCommand(command);
    }
}
