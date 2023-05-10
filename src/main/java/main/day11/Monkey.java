package main.day11;

import main.day11.operations.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Monkey {
    private final NextMonkeyFinder nextMonkeyFinder;
    List<Long> items;
    Operation operation;

    int inspectionsCount = 0;

    public Monkey(List<String> monkey, int worryDecreaseFactor) {
        this.items = getItems(monkey);
        this.operation = Operation.of(monkey.get(2), worryDecreaseFactor);
        this.nextMonkeyFinder = new NextMonkeyFinder(monkey);
    }

    int getDivider(){
        return nextMonkeyFinder.divider;
    }
    private static List<Long> getItems(List<String> monkey) {
        return Arrays.stream(monkey.get(1)
                        .replace("  Starting items: ", "")
                        .split(", "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public List<InspectionResult> inspectPart1(Integer modulo) {
        List<InspectionResult> results = new ArrayList<>();
        for(long worryLevel : items) {
            inspectionsCount = inspectionsCount + 1;

            long newWorryLevel = operation.apply(worryLevel);

            results.add(nextMonkeyFinder.getNextMonkey(newWorryLevel));
        }
        removeItems();
        return results;
    }

    public List<InspectionResult> inspectPart2(Integer modulo) {
        List<InspectionResult> results = new ArrayList<>();
        for(long worryLevel : items) {
            inspectionsCount = inspectionsCount + 1;

            long newWorryLevel = operation.apply(worryLevel) % modulo;

            results.add(nextMonkeyFinder.getNextMonkey(newWorryLevel));
        }
        removeItems();
        return results;
    }

    public void catchItem(long item){
        items.add(item);
    }

    public void removeItems(){
        this.items = new ArrayList<>();
    }

}
