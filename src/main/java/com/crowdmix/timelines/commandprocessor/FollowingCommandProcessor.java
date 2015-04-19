package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.command.FollowingCommand;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Processes FOLLOWING command.
 * 
 * @author andreypereverzin
 */
public class FollowingCommandProcessor extends AbstractCommandProcessor implements CommandProcessor<FollowingCommand> {

    public FollowingCommandProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String processCommand(FollowingCommand command) {
        User user = userManager.getOrCreateUser(command.getUsername());
        
        String userToFollowName = ((FollowingCommand)command).getUserToFollow();
        if (userManager.doesUserExist(userToFollowName)) {
            User userToFollow = userManager.getUser(userToFollowName);
            user.addFollowedUser(userToFollow);
        }
        
        return "";
    }
}
