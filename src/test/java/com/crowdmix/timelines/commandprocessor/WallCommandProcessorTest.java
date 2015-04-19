package com.crowdmix.timelines.commandprocessor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import com.crowdmix.timelines.command.WallCommand;
import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Test class for FollowingCommandProcessor.
 * 
 * @author andreypereverzin
 */
public class WallCommandProcessorTest {
    private static final String USER1 = "user1";
    private static final String USER2 = "user2";
    private static final String MESSAGE1 = "message1";
    private static final String MESSAGE2 = "message2";

    private WallCommandProcessor wallCommandProcessor;
    
    @Mock
    private UserManager userManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wallCommandProcessor = new WallCommandProcessor(userManager);
    }
    
    @Test
    public void processCommand_shouldDisplayMessagesInRightOrder() {
        // given
        User user1 = new User(USER1);
        User user2 = new User(USER2);
        Message message1 = new TestMessage(MESSAGE1, TestUtil.getTimestampInThePast(2));
        Message message2 = new TestMessage(MESSAGE2, TestUtil.getTimestampInThePast(1));
        user1.addFollowedUser(user2);
        user1.addMessage(message1);
        user2.addMessage(message2);
        WallCommand command = new WallCommand(USER1);
        when(userManager.doesUserExist(USER1)).thenReturn(true);
        when(userManager.getUser(USER1)).thenReturn(user1);
        
        // when
        String response = wallCommandProcessor.processCommand(command);
        
        // then
        assertTrue(response.contains(USER1 + " - " + MESSAGE1));
        assertTrue(response.contains(USER2 + " - " + MESSAGE2));
    }
}
