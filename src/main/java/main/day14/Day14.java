package main.day14;

import java.util.List;

public class Day14 {
    Map map;
    int width;
    int height;

    public int part1(List<String> input) {
        RockPaths rockPaths = new RockPaths(input);
        rockPaths.shift();
        height = rockPaths.getHeight();
        width = rockPaths.getWidth();
        int xShiftedSource = 500 - rockPaths.minWidth;
        map = new Map(height, width, xShiftedSource);
        drawRockLines(rockPaths);
        map.print();

        int count = 0;
        try {
            while (true) {
                putSand(new Point(xShiftedSource, 0));
                count = count + 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return count;
        }
    }

    public int part2(List<String> input) {
        RockPaths rockPaths = new RockPaths(input);
        height = rockPaths.getHeight() + 2;
        width = rockPaths.getWidth()+ 500;
        map = new Map(height, width, 500);
        drawRockLines(rockPaths);

        Point start = new Point(0, height);
        Point stop = new Point(width, height);
        map.drawRockLine(start, stop);

        int count = 0;

        Point current = new Point(width, height);
        while (!(current.x == 500 && current.y==0)) {
            current = putSand(new Point(500, 0));
            count = count + 1;
        }
        return count;
    }

    private void drawRockLines(RockPaths rockPaths) {
        for (List<Point> path : rockPaths.rockPaths) {
            for (int i = 0; i < path.size() - 1; i++) {
                Point start = path.get(i);
                Point stop = path.get(i + 1);
                map.drawRockLine(start, stop);
            }
        }
    }

    private Point putSand(Point current) {
        goDown(current);
        tryLeftDown(current);
        tryRightDown(current);

        map.drawSand(current);

        return current;
    }

    private void tryRightDown(Point current) {
        while (map.canGoRightDown(current) || map.canGoDown(current) || map.canGoLeftDown(current)) {
            if (map.canGoRightDown(current)) {
                current.goRightDown();
            }
            goDown(current);
            tryLeftDown(current);
        }
    }

    private void tryLeftDown(Point current) {
        while (map.canGoDown(current) || map.canGoLeftDown(current)) {
            if (map.canGoLeftDown(current)) {
                current.goLeftDown();
            }
            goDown(current);
        }
    }

    private Point goDown(Point point) {
        while (map.canGoDown(point)) {
            point.goDown();
        }
        return point;
    }


}


