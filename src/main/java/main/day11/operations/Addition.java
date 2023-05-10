package main.day11.operations;

public class Addition extends Operation {
    private final int factor;

    public Addition(int factor) {
        this.factor = factor;
    }

    @Override
    public long apply(long x) {
        return super.apply(factor + x);
    }
}
