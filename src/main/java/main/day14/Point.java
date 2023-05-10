package main.day14;

import static java.lang.Integer.parseInt;

public class Point {
    int x;
    int y;

    public Point(String point) {
        x = parseInt(point.split(",")[0]);
        y = parseInt(point.split(",")[1]);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void goDown(){
        y = y + 1;
    }

    void goLeftDown(){
        y = y + 1;
        x = x - 1;
    }

    void goRightDown(){
        y = y + 1;
        x = x + 1;
    }

    void shiftX(int minWidth) {
        x = x - minWidth;
    }
}
