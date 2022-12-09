package main.day9;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Day9 {
    public int part1(List<String> motions) {
        Knot head = new Knot();
        Knot tail = new Knot();

        for (String motion : motions) {
            String[] s = motion.split(" ");
            int movesNumber = parseInt(s[1]);
            String movesDirection = s[0];

            for (var i = 0; i < movesNumber; i++) {
                switch (movesDirection) {
                    case "R" -> head.moveRight();
                    case "U" -> head.moveUp();
                    case "L" -> head.moveLeft();
                    case "D" -> head.moveDown();
                }
                tail.follow(head);
            }
        }
        return tail.positions.size();
    }

    public int part2(List<String> motions) {
        List<Knot> knots = initKnots();

        for (String motion : motions) {
            String[] s = motion.split(" ");
            int movesNumber = parseInt(s[1]);
            String movesDirection = s[0];

            for (var i = 0; i < movesNumber; i++) {
                switch (movesDirection) {
                    case "R" -> knots.get(0).moveRight();
                    case "U" -> knots.get(0).moveUp();
                    case "L" -> knots.get(0).moveLeft();
                    case "D" -> knots.get(0).moveDown();
                }
                for (var j = 1; j < 10; j++) {
                    knots.get(j).follow(knots.get(j - 1));
                }
            }
        }
        return knots.get(9).positions.size();
    }

    private static List<Knot> initKnots() {
        List<Knot> knots = new ArrayList<>();

        for (var i = 0; i < 10; i++) {
            knots.add(new Knot());
        }
        return knots;
    }

}


