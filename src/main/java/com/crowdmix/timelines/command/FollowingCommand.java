package com.crowdmix.timelines.command;

import static com.crowdmix.timelines.command.CommandType.FOLLOWING;

/**
 * Encapsulates attributes of FOLLOWING command.
 * 
 * @author andreypereverzin
 */
public class FollowingCommand extends Command {
    private final String userToFollow;

    public FollowingCommand(String username, String userToFollow) {
        super(username);
        this.userToFollow = userToFollow;
    }

    public String getUserToFollow() {
        return userToFollow;
    }
    
    public CommandType getCommandType() {
        return FOLLOWING;
    }
}
