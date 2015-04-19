package com.crowdmix.timelines.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User of Timelines application.
 * 
 * @author andreypereverzin
 */
public class User {
	private final String name;
	private final SortedSet<Message> messages = new TreeSet<>();
	private final Set<User> followedUsers = new HashSet<>();

	public User(String name) {
		this.name = name;
	}

	/**
	 * Get name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get messages posted by the user.
	 */
	public Set<Message> getTimeline() {
		return Collections.unmodifiableSet(messages);
	}
	
	/**
	 * Add message to the list of posted messages.
	 */
	public void addMessage(Message message) {
		messages.add(message);
	}

	/**
	 * Get list of users followed by the user.
	 */
	public Set<User> getFollowedUsers() {
		return Collections.unmodifiableSet(followedUsers);
	}
	
	/**
	 * Add user to the list of followed users.
	 */
	public void addFollowedUser(User user) {
		followedUsers.add(user);
	}
}
