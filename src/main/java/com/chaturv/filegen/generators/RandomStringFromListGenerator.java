package com.chaturv.filegen.generators;

import java.util.List;


public class RandomStringFromListGenerator implements ValueGenerator<String> {

    private List<String> lst;

    public RandomStringFromListGenerator(List<String> lst) {
        this.lst = lst;
    }

    public String generate() {
        int idx = rand.nextInt(lst.size());
        return lst.get(idx);
    }
}




