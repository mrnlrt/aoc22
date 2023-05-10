package main.day11.operations;

public class Square extends Operation {

    @Override
    public long apply(long x) {
        return super.apply(x * x );
    }
}
