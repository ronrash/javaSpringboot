package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Copyright (C) 2022 Codesse. All rights reserved. ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class SubmissionTest {

    static ValidWords validWords;
    WordGame service;

    @BeforeClass
    public static void oneTimeSetUp() throws IOException {
        validWords = new ValidWordsImpl();
    }

    @Before
    public void setUp() {
        service = new WordGameImpl("areallylongword", validWords);
    }

    @Test
    public void testSubmission() {
        assertEquals(3, service.submitWord("player1", "all"));
        assertEquals(3, service.submitWord("player2", "aal"));
        assertEquals(3, service.submitWord("player1", "are"));
        assertEquals(4, service.submitWord("player2", "word"));
        assertEquals(0, service.submitWord("player3", "tale"));
        assertEquals(0, service.submitWord("player4", "glly"));
        assertEquals(6, service.submitWord("player5", "woolly"));
        assertEquals(6, service.submitWord("player1", "longword"));
//        assertEquals(0, service.submitWord("player1", "all"));
//        assertEquals(0, service.submitWord("player6", "woolly"));
//        assertEquals(4, service.submitWord("player5", "long"));
//        assertEquals(0, service.submitWord("player1", "all"));
//        assertEquals(0, service.submitWord("player7", "adder"));
        assertEquals("player2", service.getPlayerNameAtPosition(1));
        assertEquals("word", service.getWordEntryAtPosition(1));
    }
}
