package com.chaturv.filegen.generators;

public class IntegerRangeGenerator implements ValueGenerator<Integer> {

    private final int start;
    private final int end;

    public IntegerRangeGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Integer generate() {
        return  Double.valueOf((end - start) * rand.nextDouble() + start).intValue();
    }
}
