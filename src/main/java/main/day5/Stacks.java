package main.day5;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

@Getter
public class Stacks {
    private List<List<String>> stacks;

    public Stacks(List<String> columnStacks) {
        List<String[]> stacksOfCrates = toCratesList(columnStacks);
        String[][] transposedStacks = getTransposedStacks(stacksOfCrates);
        stacks = cleanStacks(transposedStacks);
    }

    private List<List<String>> cleanStacks(String[][] transposedStacks) {
        return new ArrayList<>(asList(transposedStacks))
                .stream()
                .map(stack -> {
                    List<String> cleanStack = new ArrayList<>(asList(stack));
                    cleanStack.removeAll(Arrays.asList("", null));
                    return cleanStack;
                })
                .collect(Collectors.toList());
    }

    private static String[][] getTransposedStacks(List<String[]> stacks) {
        int stacksNumber = stacks.get(0).length;
        int maxHeight = stacks.size();
        String[][] transposedStacks = new String[stacksNumber][maxHeight];

        for (int x = 0; x < stacksNumber; x++) {
            for (int y = 0; y < maxHeight; y++) {
                if (x <= stacks.get(y).length) {
                    if (x < stacks.get(y).length) {
                        transposedStacks[x][y] = stacks.get(y)[x];
                    }
                }
            }
        }
        return transposedStacks;
    }

    private static List<String[]> toCratesList(List<String> columnStacks) {
        Collections.reverse(columnStacks);

        return columnStacks.stream()
                .map(columnStack -> columnStack
                        .replaceAll(" {4}", " ")
                        .replaceAll("]","")
                        .replaceAll("\\[","")
                        .split(" ")
                )
                .collect(Collectors.toList());
    }

    public void moveWithCrateMover9000(int origin, int destination) {
        int indexOfCrateToMove = stacks.get(origin).size() - 1;
        String crateToMove = stacks.get(origin).get(indexOfCrateToMove);
        stacks.get(origin).remove(indexOfCrateToMove);
        stacks.get(destination).add(crateToMove);
    }

    public void moveWithCrateMover9001(int origin, int destination, int movesNumber) {
        for(var i=0;i<movesNumber;i++){
            int indexOfCrateToMove = stacks.get(origin).size() - movesNumber + i;
            String crateToMove = stacks.get(origin).get(indexOfCrateToMove);
            stacks.get(origin).remove(indexOfCrateToMove);
            stacks.get(destination).add(crateToMove);
        }
    }

    public String getTopCrates(){
        return stacks.stream()
                .map(stack -> stack.get(stack.size()-1))
                .collect(Collectors.joining());
    }
}
