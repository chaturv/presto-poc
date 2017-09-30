package com.chaturv.filegen.generators;

public class ContinuousIntegerGenerator implements ValueGenerator<Integer> {

    private int start;
    private int current;

    public ContinuousIntegerGenerator(int start) {
        this.start = start;
        this.current = 0;
    }

    public Integer generate() {
        int val = start + current;
        current++;
        return val;
    }
}
