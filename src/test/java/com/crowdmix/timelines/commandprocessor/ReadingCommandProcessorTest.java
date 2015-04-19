package com.crowdmix.timelines.commandprocessor;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import com.crowdmix.timelines.command.ReadingCommand;
import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Test class for FollowingCommandProcessor.
 * 
 * @author andreypereverzin
 */
public class ReadingCommandProcessorTest {
    private static final String USER1 = "user1";
    private static final String MESSAGE1 = "message1";
    private static final String MESSAGE2 = "message2";

    private ReadingCommandProcessor readingCommandProcessor;
    
    @Mock
    private UserManager userManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        readingCommandProcessor = new ReadingCommandProcessor(userManager);
    }
    
    @Test
    public void processCommand_shouldReturnEmptyStringIfNoMessages() {
        // given
        User user1 = new User(USER1);
        ReadingCommand command = new ReadingCommand(USER1);
        when(userManager.doesUserExist(USER1)).thenReturn(true);
        when(userManager.getUser(USER1)).thenReturn(user1);
        
        // when
        String response = readingCommandProcessor.processCommand(command);
        
        // then
        assertEquals("", response);
    }
    
    @Test
    public void processCommand_shouldReadMessagesInRightOrder() {
        // given
        User user1 = new User(USER1);
        Message message1 = new TestMessage(MESSAGE1, TestUtil.getTimestampInThePast(130));
        Message message2 = new TestMessage(MESSAGE2, TestUtil.getTimestampInThePast(40));
        user1.addMessage(message1);
        user1.addMessage(message2);
        ReadingCommand command = new ReadingCommand(USER1);
        when(userManager.doesUserExist(USER1)).thenReturn(true);
        when(userManager.getUser(USER1)).thenReturn(user1);
        
        // when
        String response = readingCommandProcessor.processCommand(command);
        
        // then
        assertTrue(response.contains(MESSAGE1));
        assertTrue(response.contains(MESSAGE2));
        assertEquals(2, user1.getTimeline().size());
        Iterator<Message> messagesIterator = user1.getTimeline().iterator();
        assertEquals(MESSAGE2, messagesIterator.next().getText());
        assertEquals(MESSAGE1, messagesIterator.next().getText());
    }
}
