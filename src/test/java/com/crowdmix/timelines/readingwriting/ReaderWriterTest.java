package com.crowdmix.timelines.readingwriting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ReaderWriter.
 * 
 * @author andreypereverzin
 */
public class ReaderWriterTest {
    private static final String BOB = "Bob";
    
    private ReaderWriter readerWriter;
    private BufferedReader br;
    private BufferedWriter bw;
    
    @Before
    public void setUp() {
    }

    @Test
    public void readLine_shouldReadLine() throws IOException {
        // given
        byte[] inputBytes = BOB.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        br = new BufferedReader(new InputStreamReader(bais));
        bw = new BufferedWriter(new OutputStreamWriter(baos));
        readerWriter = new ReaderWriter(br, bw);
        
        // when
        String line = readerWriter.readLine();
        
        // then
        assertEquals(BOB, line);
    }

    @Test
    public void writeText_shouldWriteText() throws IOException {
        // given
        byte[] inputBytes = new byte[]{};
        ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        br = new BufferedReader(new InputStreamReader(bais));
        bw = new BufferedWriter(new OutputStreamWriter(baos));
        readerWriter = new ReaderWriter(br, bw);
        
        // when
        readerWriter.writeText(BOB);
        
        // then
        assertEquals(BOB, new String(baos.toByteArray()));
    }
}
