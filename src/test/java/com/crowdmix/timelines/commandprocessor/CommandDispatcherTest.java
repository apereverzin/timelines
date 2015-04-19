package com.crowdmix.timelines.commandprocessor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import org.mockito.MockitoAnnotations;

import com.crowdmix.timelines.command.Command;
import com.crowdmix.timelines.command.CommandType;
import com.crowdmix.timelines.command.WallCommand;

/**
 * Test class for CommandDispatcher.
 * 
 * @author andreypereverzin
 *
 */
public class CommandDispatcherTest {
    private CommandDispatcher commandDispatcher;
    
    @Mock
    private CommandProcessor<WallCommand> commandProcessor;
    
    @Before
    public void setUp() {
        commandDispatcher = new CommandDispatcher();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void processCommand_shouldCallCommandProcessor() {
        // given
        commandDispatcher.registerCommandProcessor(CommandType.WALL, commandProcessor);
        WallCommand command = new WallCommand("user1");
        
        // when
        commandDispatcher.processCommand(command);
        
        // then
        verify(commandProcessor).processCommand(command);
    }

}
