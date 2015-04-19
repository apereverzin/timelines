package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.command.ReadingCommand;
import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Processes READING command.
 * 
 * @author andreypereverzin
 */
public class ReadingCommandProcessor extends DisplayingMessagesCommandProcessor implements CommandProcessor<ReadingCommand> {

    public ReadingCommandProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String processCommand(ReadingCommand command) {
        StringBuilder sb = new StringBuilder();
        
        if (userManager.doesUserExist(command.getUsername())) {
            User user = userManager.getUser(command.getUsername());
            for (Message message: user.getTimeline()) {
                sb.append(buildMessageString(message));
            }
        }
        
        return sb.toString();
    }
}
