package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.command.PostingCommand;
import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Processes POSTING command.
 * 
 * @author andreypereverzin
 */
public class PostingCommandProcessor extends AbstractCommandProcessor implements CommandProcessor<PostingCommand> {

    public PostingCommandProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String processCommand(PostingCommand command) {
        User user = userManager.getOrCreateUser(command.getUsername());
        Message message = new Message(((PostingCommand)command).getMessage());
        user.addMessage(message);
        return "";
    }
}
