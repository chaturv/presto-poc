package com.chaturv.filegen.generators;

import java.util.Random;

@FunctionalInterface
public interface ValueGenerator<T> {

    T generate();

    Random rand = new Random();
}
