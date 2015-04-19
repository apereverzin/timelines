package com.crowdmix.timelines.commandprocessor;

import java.util.SortedMap;
import java.util.TreeMap;

import com.crowdmix.timelines.command.WallCommand;
import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.domain.User;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Processes WALL command.
 * 
 * @author andreypereverzin
 */
public class WallCommandProcessor extends DisplayingMessagesCommandProcessor implements CommandProcessor<WallCommand> {

    public WallCommandProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String processCommand(WallCommand command) {
        StringBuilder sb = new StringBuilder();

        if (userManager.doesUserExist(command.getUsername())) {
            User user = userManager.getUser(command.getUsername());
            SortedMap<Message, User> messagesMap = new TreeMap<>();
            
            addAllMessagesToMap(user, messagesMap);

            addMessagesToResponse(sb, messagesMap);
        }

        return sb.toString();
    }

    private void addMessagesToResponse(StringBuilder sb, SortedMap<Message, User> messagesMap) {
        for (Message message : messagesMap.keySet()) {
            User author = messagesMap.get(message);
            sb.append(author.getName() + " - " + buildMessageString(message));
        }
    }

    private void addAllMessagesToMap(User user, SortedMap<Message, User> messagesMap) {
        addUserMessagesToMap(user, messagesMap);
        for (User userFollowing : user.getFollowedUsers()) {
            addUserMessagesToMap(userFollowing, messagesMap);
        }
    }

    private void addUserMessagesToMap(User user, SortedMap<Message, User> messagesMap) {
        for (Message message : user.getTimeline()) {
            messagesMap.put(message,  user);
        }
    }
}
