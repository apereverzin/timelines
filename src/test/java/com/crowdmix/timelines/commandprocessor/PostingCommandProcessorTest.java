package com.crowdmix.timelines.commandprocessor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import com.crowdmix.timelines.command.PostingCommand;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Test class for FollowingCommandProcessor.
 * 
 * @author andreypereverzin
 */
public class PostingCommandProcessorTest {
    private static final String USER1 = "user1";
    private static final String MESSAGE1 = "message1";

    private PostingCommandProcessor postingCommandProcessor;
    
    @Mock
    private UserManager userManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postingCommandProcessor = new PostingCommandProcessor(userManager);
    }
    
    @Test
    public void processCommand_shouldPostMessage() {
        // given
        User user1 = new User(USER1);
        PostingCommand command = new PostingCommand(USER1, MESSAGE1);
        when(userManager.getOrCreateUser(USER1)).thenReturn(user1);
        
        // when
        String response = postingCommandProcessor.processCommand(command);
        
        // then
        assertEquals("", response);
        assertEquals(1, user1.getTimeline().size());
        assertEquals(MESSAGE1, user1.getTimeline().iterator().next().getText());
    }
    
    @Test
    public void processCommand_shouldPostTwoMessages() {
        // given
        User user1 = new User(USER1);
        PostingCommand command1 = new PostingCommand(USER1, MESSAGE1);
        when(userManager.getOrCreateUser(USER1)).thenReturn(user1);
        
        // when
        String response1 = postingCommandProcessor.processCommand(command1);
        
        // then
        assertEquals("", response1);
        assertEquals(1, user1.getTimeline().size());
    }
}
