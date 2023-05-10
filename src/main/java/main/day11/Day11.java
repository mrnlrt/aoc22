package main.day11;

import org.apache.commons.collections4.ListUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;

public class Day11 {
    public long part1(List<String> input) {
        List<Monkey> monkeys = getMonkeys(input, 3);
        for (var i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                List<InspectionResult> results = monkey.inspectPart1(1);
                results.forEach(result ->
                        monkeys.get(result.monkeyNumber)
                                .catchItem(result.worryLevel)
                );
            }
        }

        return getCount(monkeys);
    }


    public long part2(List<String> input) {
        List<Monkey> monkeys = getMonkeys(input, 1);
        Integer modulo = getModulo(monkeys);
        for (var i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                List<InspectionResult> results = monkey.inspectPart2(modulo);
                results.forEach(result ->
                        {
                            int monkeyNumber = result.monkeyNumber;
                            long worryLevel = result.worryLevel;
                            monkeys.get(monkeyNumber).catchItem(worryLevel);
                        }
                );
            }
        }
        return getCount(monkeys);
    }

    private static Integer getModulo(List<Monkey> monkeys) {
        return monkeys.stream()
                .map(Monkey::getDivider)
                .reduce(1, (a, b) -> a * b);
    }

    private static List<Monkey> getMonkeys(List<String> input, int worryDecreaseFactor) {
        return ListUtils.partition(input, 7)
                .stream()
                .map(monkey -> new Monkey(monkey, worryDecreaseFactor))
                .collect(Collectors.toList());
    }

    private static long getCount(List<Monkey> monkeys) {
        List<Long> count = monkeys.stream()
                .map(monkey -> (long) monkey.inspectionsCount)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();
        return count.get(0) * count.get(1);
    }
}


