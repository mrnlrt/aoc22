package main.day11.operations;

public class Multiplication extends Operation {
    private final int factor;

    public Multiplication(int factor) {
        super();
        this.factor = factor;
    }

    public long apply(long x){
        return super.apply(factor * x);
    }
}
