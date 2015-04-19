package com.crowdmix.timelines.readingwriting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Responsible for communicating to user.
 * 
 * @author andreypereverzin
 */
public class ReaderWriter {
    private static final String PROMPT = "> ";
    
    private final BufferedReader br;
    private final BufferedWriter bw;
    
    public ReaderWriter(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }
    
    public String readLine() throws IOException {
        bw.write(PROMPT);
        bw.flush();
        return br.readLine();
    }

    public void writeText(String line) throws IOException {
        bw.write(line);
        bw.flush();
    }
}
