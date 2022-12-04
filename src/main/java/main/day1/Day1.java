package main.day1;

import main.FileParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Collections.max;

public class Day1 {

    public int part1(String fileName) {
        List<String> file = new FileParser(getClass()).parse(fileName);
        List<Integer> caloriesByElf = getCaloriesByElf(file);

        return caloriesByElf.get(0);
    }

    public int part2(String fileName) {
        List<String> file = new FileParser(getClass()).parse(fileName);
        List<Integer> caloriesByElf = getCaloriesByElf(file);

        return caloriesByElf.stream()
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<Integer> getCaloriesByElf(List<String> file) {
        List<Integer> caloriesByElf = new ArrayList<>();
        int currentElfCalories= 0;
        for(String calories: file){
            if("".equals(calories)){
                caloriesByElf.add(currentElfCalories);
                currentElfCalories = 0;
            }
            else{
                currentElfCalories+=parseInt(calories);
            }
        }
        caloriesByElf.sort(Comparator.reverseOrder());
        return caloriesByElf;
    }
}