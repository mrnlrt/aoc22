package main.day14;

import java.util.List;

import static java.util.Objects.isNull;

public class Map {
    String[][] map;
    int width;
    int height;

    public Map(int height, int width, int xSource) {
        map = new String[height + 1][width + 1];
        this.height = height + 1;
        this.width = width + 1;
        map[0][xSource] = "+";

    }

    void drawRockLine(Point start, Point stop ){
        if (start.x < stop.x) {
            for (int i = start.x; i <= stop.x; i++) {
                map[start.y][i] = "#";
            }
        } else if (start.x > stop.x) {
            for (int i = start.x; i >= stop.x; i--) {
                map[start.y][i] = "#";

            }
        } else if (start.y > stop.y) {
            for (int i = start.y; i >= stop.y; i--) {
                map[i][start.x] = "#";

            }
        } else if (start.y < stop.y) {
            for (int i = start.y; i <= stop.y; i++) {
                map[i][start.x] = "#";

            }
        }
    }
    void drawSand(Point point){
        map[point.y][point.x] = "o";
    }

    boolean canGoRightDown(Point current) {
        return isNull(map[current.y + 1][current.x + 1]);
    }

    boolean canGoLeftDown(Point current) {
        return isNull(map[current.y + 1][current.x - 1]);
    }

    boolean canGoDown(Point point) {
        return isNull(map[point.y + 1][point.x]);
    }

    void print() {
        for (var i = 0; i < height; i++) {
            for (var j = 0; j < width; j++) {
                if (isNull(map[i][j])) {
                    System.out.print(".");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }
}
