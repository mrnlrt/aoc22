package main.day12;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;

public class Day12 {
    public int part1(List<String> heightMap) {
        HeightMap map = new HeightMap(heightMap);

        return findLengthOfPath(map);
    }

    int findLengthOfPath(HeightMap map) {
        map.explore();

        return map.getEndCost();
    }

    public int part2(List<String> heightMap) {
        int shortestPath = Integer.MAX_VALUE;
        HeightMap map = new HeightMap(heightMap);
        List<Cell> possibleStartCells = map.getPossibleStartCells();
        for (Cell startCell : possibleStartCells) {
            map.explore(startCell);

            int endCost = map.getEndCost();
            if (endCost>0 && endCost < shortestPath) {
                shortestPath = endCost;
            }
            map.reset();
        }
        return shortestPath;
    }

}

