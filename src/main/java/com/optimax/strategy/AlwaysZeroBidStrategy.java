package com.optimax.strategy;

public class AlwaysZeroBidStrategy implements BidStrategy {
    public int bid() {
        return 0;
    }
}
