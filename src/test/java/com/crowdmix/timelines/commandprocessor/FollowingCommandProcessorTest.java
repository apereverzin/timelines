package com.crowdmix.timelines.commandprocessor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import com.crowdmix.timelines.command.FollowingCommand;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Test class for FollowingCommandProcessor.
 * 
 * @author andreypereverzin
 */
public class FollowingCommandProcessorTest {
    private static final String USER2 = "user2";
    private static final String USER1 = "user1";

    private FollowingCommandProcessor followingCommandProcessor;
    
    @Mock
    private UserManager userManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        followingCommandProcessor = new FollowingCommandProcessor(userManager);
    }
    
    @Test
    public void processCommand_shouldProcessFollowingCommand() {
        // given
        User user1 = new User(USER1);
        User user2 = new User(USER2);
        FollowingCommand command = new FollowingCommand(USER1, USER2);
        when(userManager.getOrCreateUser(USER1)).thenReturn(user1);
        when(userManager.doesUserExist(USER2)).thenReturn(true);
        when(userManager.getUser(USER2)).thenReturn(user2);
        
        // when
        String response = followingCommandProcessor.processCommand(command);
        
        // then
        assertEquals("", response);
        assertTrue(user1.getFollowedUsers().contains(user2));
    }
}
