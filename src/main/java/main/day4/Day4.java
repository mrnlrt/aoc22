package main.day4;

import main.FileParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static org.apache.commons.collections4.CollectionUtils.containsAll;
import static org.apache.commons.collections4.CollectionUtils.containsAny;

public class Day4 {

    public long part1(List<String> pairs) {
        return pairs.stream()
                .map(pair -> List.of(pair.split(",")))
                .map(Day4::getPairRanges)
                .filter(this::pairFullyOverlapping)
                .count();
    }

    private boolean pairFullyOverlapping(List<List<Integer>> ranges) {
        boolean firstElfHasSecondElfAssignment = containsAll(ranges.get(0), ranges.get(1));
        boolean secondElfHasFirstElfAssignment = containsAll(ranges.get(1), ranges.get(0));
        return firstElfHasSecondElfAssignment || secondElfHasFirstElfAssignment;
    }

    public long part2(List<String> pairs) {
        return pairs.stream()
                .map(pair -> List.of(pair.split(",")))
                .map(Day4::getPairRanges)
                .filter(this::pairOverlapping)
                .count();
    }

    private boolean pairOverlapping(List<List<Integer>> ranges) {
        return containsAny(ranges.get(0), ranges.get(1));
    }

    private static List<List<Integer>> getPairRanges(List<String> pairAssignments) {
        return pairAssignments
                .stream()
                .map(elfAssignment -> {
                    int[] range = Arrays.stream(elfAssignment.split("-")).mapToInt(i -> parseInt(i)).toArray();
                    return IntStream
                            .rangeClosed(range[0], range[1])
                            .boxed()
                            .collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }
}


