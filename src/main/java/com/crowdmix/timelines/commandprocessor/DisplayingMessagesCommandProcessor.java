package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.domain.Message;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Abstract superclass for command processors displaying list of messages.
 * 
 * @author andreypereverzin
 */
public abstract class DisplayingMessagesCommandProcessor extends AbstractCommandProcessor {
    protected final static long MILLISECONDS_IN_MINUTE = 60 * 1000;
    protected final static long MILLISECONDS_IN_SECOND = 1000;
    private static final String MINUTE = "minute";
    private static final String MINUTES = "minutes";
    private static final String SECOND = "second";
    private static final String SECONDS = "seconds";
    private static final String AGO = "ago";

    public DisplayingMessagesCommandProcessor(UserManager userManager) {
        super(userManager);
    }
 
    protected String buildMessageString(Message message) {
        String agoText = buildAgoText(message);
        
        return message.getText() + " (" + agoText + " " + AGO + ")\n";
    }

    private String buildAgoText(Message message) {
        String agoText;
        
        long ago = System.currentTimeMillis() - message.getTimestamp();
        if (ago < MILLISECONDS_IN_MINUTE) {
            long secondsAgo = ago / MILLISECONDS_IN_SECOND;
            agoText = secondsAgo + " " + (secondsAgo == 1 ? SECOND : SECONDS);
        } else {
            long minutesAgo = (System.currentTimeMillis() - message.getTimestamp()) / MILLISECONDS_IN_MINUTE;
            agoText = minutesAgo + " " + (minutesAgo == 1 ? MINUTE : MINUTES);
        }
        
        return agoText;
    }
}
