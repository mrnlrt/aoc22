package main.day2;

import main.FileParser;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Day2 {
    public static final String OPPONENT_ROCK = "A";
    public static final String OPPONENT_PAPER = "B";
    public static final String OPPONENT_SCISSORS = "C";
    public static final String ROCK = "X";
    public static final String PAPER = "Y";
    public static final String SCISSORS = "Z";
    public static final Map<String, Map<String, Integer>> PLAY_ISSUES_SCORES = Map.of(
            OPPONENT_ROCK, Map.of(PAPER, 6, ROCK, 3, SCISSORS, 0),
            OPPONENT_PAPER, Map.of(SCISSORS, 6, PAPER, 3, ROCK, 0),
            OPPONENT_SCISSORS, Map.of(ROCK, 6, SCISSORS, 3, PAPER, 0)
    );
    public static final Map<String, Integer> SCORES = Map.of(ROCK, 1, PAPER, 2, SCISSORS, 3);

    public int part1(List<String> strategyGuide) {

        return strategyGuide.stream()
                .map(play -> play.split(" "))
                .mapToInt(Day2::roundScorePart1)
                .sum();
    }

    public int part2(List<String> strategyGuide) {

        return strategyGuide.stream()
                .map(play -> play.split(" "))
                .mapToInt(Day2::roundScorePart2)
                .sum();
    }

    private static int roundScorePart1(String[] moves) {
        String opponentMove = moves[0];
        String myMove = moves[1];
        return PLAY_ISSUES_SCORES.get(opponentMove).get(myMove) + SCORES.get(myMove);
    }
    private static int roundScorePart2(String[] moves) {
        Map<String, Map<String, String>> playMoves = Map.of(
                OPPONENT_ROCK, Map.of( "Z",PAPER, "Y",ROCK, "X", SCISSORS),
                OPPONENT_PAPER, Map.of( "Z",SCISSORS, "Y",PAPER, "X", ROCK),
                OPPONENT_SCISSORS, Map.of( "Z",ROCK, "Y",SCISSORS, "X", PAPER)
        );
        String opponentMove = moves[0];
        String playIssue = moves[1];
        String myMove = playMoves.get(opponentMove).get(playIssue);
        return PLAY_ISSUES_SCORES.get(opponentMove).get(myMove) + SCORES.get(myMove);

    }
}


