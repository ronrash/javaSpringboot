package com.codesse.codetest.wordgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (C) 2022 Codesse. All rights reserved. ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class ValidWordsImpl implements ValidWords {

    Set<String> set = new HashSet<>();

    public ValidWordsImpl() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/wordlist.txt"),
                StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                set.add(inputLine);
            }
        }

    }

    @Override
    public boolean contains(String word) {
        return set.contains(word);
    }

    @Override
    public int size() {
        return set.size();
    }
}
