package com.crowdmix.timelines.commandprocessor;

import org.junit.Before;
import org.junit.Test;

import com.crowdmix.timelines.command.Command;
import com.crowdmix.timelines.command.FollowingCommand;
import com.crowdmix.timelines.command.PostingCommand;
import com.crowdmix.timelines.command.ReadingCommand;
import com.crowdmix.timelines.command.WallCommand;
import com.crowdmix.timelines.commandprocessor.LineParser;
import com.crowdmix.timelines.commandprocessor.CommandParseException;

import static com.crowdmix.timelines.command.CommandType.FOLLOWING;
import static com.crowdmix.timelines.command.CommandType.POSTING;
import static com.crowdmix.timelines.command.CommandType.READING;
import static com.crowdmix.timelines.command.CommandType.WALL;
import static org.junit.Assert.assertEquals;

/**
 * Test class for LineParser.
 * 
 * @author andreypereverzin
 */
public class LineParserTest {
    private LineParser lineParser;
    
    @Before
    public void setUp() {
        lineParser = new LineParser();
    }
    
    @Test
    public void parseLine_shouldParsePostingCommand() throws CommandParseException {
        // given
        String line = "Bob -> message";
        
        // when
        Command command = lineParser.parseLine(line);
        
        // then
        assertEquals(POSTING, command.getCommandType());
        PostingCommand postingCommand = (PostingCommand)command;
        assertEquals("Bob", postingCommand.getUsername());
        assertEquals("message", postingCommand.getMessage());
    }
    
    @Test
    public void parseLine_shouldParsePostingCommandMessageWithSpaces() throws CommandParseException {
        // given
        String line = "Bob -> message text";
        
        // when
        Command command = lineParser.parseLine(line);
        
        // then
        assertEquals(POSTING, command.getCommandType());
        PostingCommand postingCommand = (PostingCommand)command;
        assertEquals("Bob", postingCommand.getUsername());
        assertEquals("message text", postingCommand.getMessage());
    }
    
    @Test
    public void parseLine_shouldParseReadingCommand() throws CommandParseException {
        // given
        String line = "Bob";
        
        // when
        Command command = lineParser.parseLine(line);
        
        // then
        assertEquals(READING, command.getCommandType());
        ReadingCommand readingCommand = (ReadingCommand)command;
        assertEquals("Bob", readingCommand.getUsername());
    }
    
    @Test
    public void parseLine_shouldParseFollowingCommand() throws CommandParseException {
        // given
        String line = "Bob following Alice";
        
        // when
        Command command = lineParser.parseLine(line);
        
        // then
        assertEquals(FOLLOWING, command.getCommandType());
        FollowingCommand followingCommand = (FollowingCommand)command;
        assertEquals("Bob", followingCommand.getUsername());
        assertEquals("Alice", followingCommand.getUserToFollow());
    }
    
    @Test
    public void parseLine_shouldParseWallCommand() throws CommandParseException {
        // given
        String line = "Bob wall";
        
        // when
        Command command = lineParser.parseLine(line);
        
        // then
        assertEquals(WALL, command.getCommandType());
        WallCommand wallCommand = (WallCommand)command;
        assertEquals("Bob", wallCommand.getUsername());
    }
    
    @Test(expected = CommandParseException.class)
    public void parseLine_shouldThrowExceptionIfWrongCommand() throws CommandParseException {
        // given
        String line = "Bob wrongcommand";
        
        // when
        lineParser.parseLine(line);
        
        // then
        // exception
    }
}
