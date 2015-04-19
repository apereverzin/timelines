package com.crowdmix.timelines.usermanagement;

import java.util.HashMap;
import java.util.Map;

import com.crowdmix.timelines.domain.User;

/**
 * Performs all operations with users.
 * 
 * @author andreypereverzin
 */
public class UserManager {
    private final Map<String, User> users = new HashMap<>();
    
    /**
     * Get User by username or create new one if does not exist.
     * 
     * @param username
     * @return
     */
    public User getOrCreateUser(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username));
        }
        
        return users.get(username);
    }

    /**
     * Get User by username.
     * 
     * @param username
     * @return
     */
    public User getUser(String username) {
        return users.get(username);
    }

    /**
     * Check user's existence.
     */
    public boolean doesUserExist(String username) {
        return users.containsKey(username);
    }
}
