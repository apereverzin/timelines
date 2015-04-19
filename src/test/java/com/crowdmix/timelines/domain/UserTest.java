package com.crowdmix.timelines.domain;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for User.
 * 
 * @author andreypereverzin
 */
public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("name");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getMessages_shouldReturnUnmodifiableList() {
        Set<Message> messages = user.getTimeline();

        messages.add(new Message("text"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getFollowedUser_shouldReturnUnmodifiableSet() {
        Set<User> following = user.getFollowedUsers();

        following.add(new User("name1"));
    }
}
