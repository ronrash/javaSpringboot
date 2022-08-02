package com.codesse.codetest.wordgame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * This is the shell implementation of the WordGame interface. It is the class that you should focus
 * on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

    public static final int LEADER_BOARD_LIMIT = 5;
    private final String inputWord;
    private final ValidWords validWords;

    public WordGameImpl(final String inputWord, final ValidWords validWords) {
        this.inputWord = inputWord;
        this.validWords = validWords;
    }

    static List<Player> leaderBoard = new ArrayList<>();

    @Override
    public synchronized int submitWord(final String playerName, final String userInput) {
        if (validWords.contains(userInput) && isWordValid(userInput)) {
            Player player = new Player(userInput.length(), playerName, userInput);
            if (leaderBoard.contains(player) || !checkIfElementHasToInserted(player))
                return 0;
            leaderBoard.add(player);
            Collections.sort(leaderBoard, new PlayerRankingComparator());
            if(leaderBoard.size()> LEADER_BOARD_LIMIT)
            {
                leaderBoard.remove(leaderBoard.size()-1);
            }
            return userInput.length();
        } else {
            return 0;
        }
    }

    private boolean checkIfElementHasToInserted(Player player) {
        if (leaderBoard.size() > LEADER_BOARD_LIMIT-1) {
            final Player player1 = leaderBoard.get(leaderBoard.size()-1);
            return player1.getScore() <= player.getScore();
        }
        return true;
    }


    @Override
    public String getPlayerNameAtPosition(final int position) {
        final Player player = leaderBoard.get(position);
        return player.getName();
    }

    @Override
    public String getWordEntryAtPosition(final int position) {
        final Player player = leaderBoard.get(position);
        return player.getWord();
    }

    @Override
    public Integer getScoreAtPosition(final int position) {

        final Player player = leaderBoard.get(position);
        return player.getScore();
    }

    private boolean isWordValid(final String word) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Character ch : inputWord.toCharArray()) {
            String temp = String.valueOf(ch);
            map.put(temp, map.containsKey(temp) ? map.get(temp) + 1 : 1);
        }
        for (int i = 0; i < word.length(); i++) {
            final char charAt = word.charAt(i);
            String temp = String.valueOf(charAt);
            if (!map.containsKey(temp)) {
                return false;
            }
            map.put(temp, map.get(temp) - 1);
            final Integer count = map.get(temp);
            if (count == 0)
                map.remove(temp);
        }
        return true;
    }

    static class Player {

        int score;
        String name;
        String word;

        public Player(final int score, final String name, String word) {
            this.score = score;
            this.name = name;
            this.word = word;
        }

        public int getScore() {
            return score;
        }

        public void setScore(final int score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getWord() {
            return word;
        }

        public void setWord(final String word) {
            this.word = word;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Player player = (Player) o;
            return word.equals(player.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }
    }

    static class PlayerRankingComparator implements Comparator<Player> {

        @Override
        public int compare(Player firstPlayer, Player secondPlayer) {
            return Integer.compare(secondPlayer.getScore(), firstPlayer.getScore());
        }
    }
}
