package com.chaturv.filegen.generators;

import java.time.LocalDate;

public class ContinuousDatesGenerator implements ValueGenerator<LocalDate>{

    private LocalDate startDate;
    private int noOfRows;
    private int current;
    private int daysToAdd;

    private int randomLookBack;

    public ContinuousDatesGenerator(LocalDate startDate, int noOfRows, int randomLookBack) {
        this.startDate = startDate;
        this.noOfRows = noOfRows;
        this.randomLookBack = randomLookBack;
        this.current = 0;
        this.daysToAdd = 0;
    }

    public LocalDate generate() {
        if (current == noOfRows) {
            current = 0;
            daysToAdd++;
        }

        LocalDate dt = startDate.plusDays(daysToAdd).minusDays(rand.nextInt(randomLookBack + 1));
        current++;
        return dt;
    }
}
