package com.crowdmix.timelines.usermanagement;

import org.junit.Before;
import org.junit.Test;

import com.crowdmix.timelines.domain.User;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for UserManager.
 * 
 * @author andreypereverzin
 */
public class UserManagerTest {
    private UserManager userManager;
    private static final String USER1 = "user1";
    
    @Before
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void getUser_shouldReturnNullIfUserDoesNotExist() {
        // given
        assertFalse(userManager.doesUserExist(USER1));
        
        // when
        User user = userManager.getUser(USER1);
        
        // then
        assertNull(user);
    }

    @Test
    public void getOrCreateUser_shouldCreateNewUser() {
        // given
        assertFalse(userManager.doesUserExist(USER1));
        
        // when
        userManager.getOrCreateUser(USER1);
        
        // then
        assertTrue(userManager.doesUserExist(USER1));
    }

    @Test
    public void getUser_shouldReturnUser() {
        // given
        assertFalse(userManager.doesUserExist(USER1));
        userManager.getOrCreateUser(USER1);
        
        // when
        User user = userManager.getUser(USER1);
        
        // then
        assertNotNull(user);
    }
}
