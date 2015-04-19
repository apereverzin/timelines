package com.crowdmix.timelines.commandprocessor;

/**
 * Utility class for testing purposes.
 * 
 * @author andreypereverzin
 */
public class TestUtil {
    private static final long MILLISECONDS_IN_SECOND = 1000;
    
    public static long getTimestampInThePast(int numberOfMinutesAgo) {
        return System.currentTimeMillis() - numberOfMinutesAgo * MILLISECONDS_IN_SECOND;
    }
}
