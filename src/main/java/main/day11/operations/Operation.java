package main.day11.operations;

import static java.lang.Integer.parseInt;

public class Operation {
    private static int worryDecreaseFactor;

    public static Operation of(String input, int worryDecreaseFactor) {
        Operation.worryDecreaseFactor = worryDecreaseFactor;
        String operation = input.replace("  Operation: new = old ", "");
        if (operation.contains("*")) {
            if (operation.contains("old")) {
                return new Square();
            } else {
                int factor = parseInt(operation.replace("* ", ""));
                return new Multiplication(factor);
            }
        } else {
            int factor = parseInt(operation.replace("+ ", ""));
            return new Addition(factor);
        }
    }

    public long apply(long x){
        return x / worryDecreaseFactor;
    }
}
