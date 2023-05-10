package main.day15;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Point {
    int x;
    int y;
    Point beacon;
    private int distance;

    public Point(String sensor) {
        String[] points = sensor
                .replace("Sensor at ","")
                .replace(" closest beacon is at ", "")
                .split(":");
        String[] current = points[0].replace("x=", "")
                .replace("y=", "")
                .split(", ");
        String[] beacon = points[1].replace("x=", "")
                .replace("y=", "")
                .split(", ");
        x = parseInt(current[0]);
        y = parseInt(current[1]);
        this.beacon = new Point(parseInt(beacon[0]),parseInt(beacon[1]));
        manhattanDistance();
    }

    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public void manhattanDistance() {
        distance = abs(x - beacon.x) + abs(y - beacon.y);
    }

    public List<Integer> rowDistance(int row) {
       int xMin = x - distance + abs(y - row);
       int xMax = distance - abs(y - row) + x;
       List<Integer> visited = new ArrayList<>();
       for(int i=xMin;i<=xMax;i++){
           visited.add(i);
       }
       return visited;
    }

    public int getXMax(int row) {
        return distance - abs(y - row) + x;
    }

    public int getXMin(int row) {
        return x - distance + abs(y - row);
    }

}
