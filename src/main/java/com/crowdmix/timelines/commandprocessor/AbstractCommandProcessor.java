package com.crowdmix.timelines.commandprocessor;

import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Abstract superclass for command processors.
 * 
 * @author andreypereverzin
 */
public abstract class AbstractCommandProcessor {
    protected final UserManager userManager;

    public AbstractCommandProcessor(UserManager userManager) {
        this.userManager = userManager;
    }
}
