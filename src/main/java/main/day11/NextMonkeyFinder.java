package main.day11;

import java.util.List;

import static java.lang.Integer.parseInt;

public class NextMonkeyFinder {
    final int divider;
    private final int ifMultiple;
    private final int ifNotAMultiple;

    public NextMonkeyFinder(List<String> monkey) {
        this.divider = parseInt(monkey.get(3)
                .replace("  Test: divisible by ",""));
        this.ifMultiple = parseInt(monkey.get(4)
                .replace("    If true: throw to monkey ",""));

        this.ifNotAMultiple = parseInt(monkey.get(5)
                .replace("    If false: throw to monkey ",""));
    }

    InspectionResult getNextMonkey(long worryLevel){
        if(worryLevel % divider == 0){
            return new InspectionResult(ifMultiple, worryLevel);
        }
        return new InspectionResult(ifNotAMultiple, worryLevel);
    }
}
