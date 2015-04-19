package com.crowdmix.timelines.commandprocessor;

import java.util.StringTokenizer;

import com.crowdmix.timelines.command.Command;
import com.crowdmix.timelines.command.FollowingCommand;
import com.crowdmix.timelines.command.PostingCommand;
import com.crowdmix.timelines.command.ReadingCommand;
import com.crowdmix.timelines.command.WallCommand;

/**
 * Parses lines entered by users.
 * 
 * TODO Better analysis for wrong input should be implemented.
 * 
 * @author andreypereverzin
 */
public class LineParser {
    private static final String WALL = "wall";
    private static final String FOLLOWING = "following";
    private static final String POSTING = "->";

    public Command parseLine(String line) throws CommandParseException {
        StringTokenizer st = new StringTokenizer(line, " ");
        int numberOfTokens = st.countTokens();
        String username = st.nextToken();
        if (numberOfTokens == 1) {
            return new ReadingCommand(username);
        } else {
            String secondToken = st.nextToken();
            if (numberOfTokens == 2 && secondToken.equals(WALL)) {
                return new WallCommand(username);
            } else if (numberOfTokens == 3 && secondToken.equals(FOLLOWING)) {
                String userToFollow = st.nextToken();
                return new FollowingCommand(username, userToFollow);
            } else if (numberOfTokens >= 3 && secondToken.equals(POSTING)) {
                String text = readRemainingTokens(st);
                return new PostingCommand(username, text);
            }
        }
        
        throw new CommandParseException(line);
    }

    private String readRemainingTokens(StringTokenizer st) {
        String text = "";
        while (st.hasMoreTokens()) {
            text += st.nextToken();
            if (st.hasMoreTokens()) {
                text += " ";
            }
        }
        return text;
    }
}
