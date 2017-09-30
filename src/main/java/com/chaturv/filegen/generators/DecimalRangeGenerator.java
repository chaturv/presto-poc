package com.chaturv.filegen.generators;

import java.math.BigDecimal;

public class DecimalRangeGenerator implements ValueGenerator<BigDecimal> {

    private final BigDecimal start;
    private final BigDecimal end;

    public DecimalRangeGenerator(BigDecimal start, BigDecimal end) {
        this.start = start;
        this.end = end;
    }

    public BigDecimal generate() {
        return (end.subtract(start)).multiply(BigDecimal.valueOf(rand.nextDouble())).add(start);
    }
}
