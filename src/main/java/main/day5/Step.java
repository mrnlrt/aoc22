package main.day5;

import lombok.Getter;

import static java.lang.Integer.parseInt;

@Getter
public class Step {
    private final int movesNumber;
    private final int origin;
    private final int destination;

    public Step(String step) {
        String[] cleanStep = getCleanStep(step);
        this.movesNumber = parseInt(cleanStep[0]);
        this.origin = parseInt(cleanStep[1]) - 1;
        this.destination = parseInt(cleanStep[2]) - 1;
    }

    private static String[] getCleanStep(String step) {
        return step
                .replace("move ", "")
                .replace("from ", "")
                .replace("to ", "")
                .split(" ");
    }


    public void applyCrateMover9000(Stacks stacks) {
        for (int i = 0; i < movesNumber; i++) {
            stacks.moveWithCrateMover9000(origin, destination);
        }
    }

    public void applyCrateMover9001(Stacks stacks) {
        stacks.moveWithCrateMover9001(origin, destination, movesNumber);
    }
}
