package main.day5;

import main.FileParser;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static org.apache.commons.collections4.CollectionUtils.containsAny;

public class Day5 {

    public String part1(List<String> procedure) {
        int stacksEndIndex = procedure.indexOf("");
        Stacks stacks = new Stacks(procedure.subList(0, stacksEndIndex - 1));
        List<Step> procedureSteps = getSteps(procedure.subList(stacksEndIndex + 1, procedure.size()));

        for (Step step : procedureSteps) {
            step.applyCrateMover9000(stacks);
        }


        return stacks.getTopCrates();
    }

    public String part2(List<String> procedure) {
        int stacksEndIndex = procedure.indexOf("");
        Stacks stacks = new Stacks(procedure.subList(0, stacksEndIndex - 1));
        List<Step> procedureSteps = getSteps(procedure.subList(stacksEndIndex + 1, procedure.size()));

        for (Step step : procedureSteps) {
            step.applyCrateMover9001(stacks);
        }


        return stacks.getTopCrates();
    }

    private List<Step> getSteps(List<String> steps) {
        return steps.stream()
                .map(Step::new)
                .collect(Collectors.toList());
    }

}


