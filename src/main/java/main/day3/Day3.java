package main.day3;

import main.FileParser;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Day3 {
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int part1(String fileName) {
        List<List<String>> rucksacks = new FileParser(getClass()).parse(fileName)
                .stream()
                .map(rucksack -> asList(rucksack.split("")))
                .collect(Collectors.toList());
        List<String> commonElements = rucksacks.stream()
                .map(Day3::getRucksackCommonElement)
                .collect(Collectors.toList());
        return getCommonElementsPrioritiesSum(commonElements);
    }

    private static String getRucksackCommonElement(List<String> rucksack) {
        int size = rucksack.size();
        List<String> compartment1 = new ArrayList<>(rucksack.subList(0, (size + 1) / 2));
        List<String> compartment2 = rucksack.subList((size + 1) / 2, size);
        compartment1.retainAll(compartment2);
        return compartment1.get(0);
    }

    private static int getCommonElementsPrioritiesSum(List<String> commonElements) {
        return commonElements.stream()
                .mapToInt(commonElement -> LETTERS.indexOf(commonElement) + 1)
                .sum();
    }

    public int part2(String fileName) {
        List<List<String>> rucksacks = new FileParser(getClass()).parse(fileName)
                .stream()
                .map(rucksack -> asList(rucksack.split("")))
                .collect(Collectors.toList());

        List<List<List<String>>> groups = ListUtils.partition(rucksacks, 3);

        List<String> commonElements = groups.stream()
                .map(Day3::getCommonElementInGroup)
                .collect(Collectors.toList());
        return getCommonElementsPrioritiesSum(commonElements);
    }

    private static String getCommonElementInGroup(List<List<String>> group) {
        List<String> firstGroup = new ArrayList<>(group.get(0));
        firstGroup.retainAll(group.get(1));
        firstGroup.retainAll(group.get(2));
        return firstGroup.get(0);
    }
}


