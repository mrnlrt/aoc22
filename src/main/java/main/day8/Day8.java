package main.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day8 {
    public int part1(List<String> trees) {
        List<List<Integer>> treesMap = getMap(trees);
        TreesMap map = new TreesMap(treesMap);
        return map.getVisibleTrees();
    }

    public int part2(List<String> trees) {
        List<List<Integer>> treesMap = getMap(trees);
        TreesMap map = new TreesMap(treesMap);
        return map.getMaxScenicScore();
    }

    private static List<List<Integer>> getMap(List<String> trees) {
        List<List<Integer>> treesMap = trees.stream()
                .map(treeRow -> Arrays.stream(treeRow.split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
        return treesMap;
    }
}


