package main.day9;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knot {
    int x = 0;
    int y = 0;
    Set<List<Integer>> positions = new HashSet<>();

    public void follow(Knot previous) {
        if (!closeTo(previous)) {
            if (x < previous.x) {
                moveRight();
            } else if (x > previous.x) {
                moveLeft();
            }
            if (y < previous.y) {
                moveUp();
            } else if (y > previous.y) {
                moveDown();
            }
        }
        positions.add(List.of(x, y));
    }

    public void moveDown() {
        y = y - 1;
    }

    public void moveUp() {
        y = y + 1;
    }

    public void moveLeft() {
        x = x - 1;
    }

    public void moveRight() {
        x = x + 1;
    }

    private boolean closeTo(Knot previous) {
        return x >= previous.x - 1 &&
               x <= previous.x + 1 &&
               y >= previous.y - 1 &&
               y <= previous.y + 1;
    }
}
