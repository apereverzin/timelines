package com.crowdmix.timelines;

import static com.crowdmix.timelines.command.CommandType.FOLLOWING;
import static com.crowdmix.timelines.command.CommandType.POSTING;
import static com.crowdmix.timelines.command.CommandType.READING;
import static com.crowdmix.timelines.command.CommandType.WALL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.crowdmix.timelines.command.Command;
import com.crowdmix.timelines.commandprocessor.CommandDispatcher;
import com.crowdmix.timelines.commandprocessor.FollowingCommandProcessor;
import com.crowdmix.timelines.commandprocessor.LineParser;
import com.crowdmix.timelines.commandprocessor.PostingCommandProcessor;
import com.crowdmix.timelines.commandprocessor.ReadingCommandProcessor;
import com.crowdmix.timelines.commandprocessor.CommandParseException;
import com.crowdmix.timelines.commandprocessor.WallCommandProcessor;
import com.crowdmix.timelines.readingwriting.ReaderWriter;
import com.crowdmix.timelines.usermanagement.UserManager;

/**
 * Starts Timelines application.
 * 
 * @author andreypereverzin
 */
public class Timelines {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private ReaderWriter readerWriter = new ReaderWriter(br, bw);
    private LineParser lineParser = new LineParser();
    private UserManager userManager = new UserManager();
    private CommandDispatcher commandDispatcher = new CommandDispatcher();

    public static void main(String[] args) {
        Timelines timelines = new Timelines();
        timelines.launch();
    }

    private void launch() {
        registerCommandProcessors();
        
        while (true) {
            processCommand();
        }
    }

    private void registerCommandProcessors() {
        commandDispatcher.registerCommandProcessor(POSTING, new PostingCommandProcessor(userManager));
        commandDispatcher.registerCommandProcessor(READING, new ReadingCommandProcessor(userManager));
        commandDispatcher.registerCommandProcessor(FOLLOWING, new FollowingCommandProcessor(userManager));
        commandDispatcher.registerCommandProcessor(WALL, new WallCommandProcessor(userManager));
    }

    private void processCommand() {
        try {
            String line = readerWriter.readLine();
            Command command = lineParser.parseLine(line);
            String response = commandDispatcher.processCommand(command);
            readerWriter.writeText(response);
        } catch (IOException | CommandParseException ex) {
            // TODO process exception better
        }
    }
}
