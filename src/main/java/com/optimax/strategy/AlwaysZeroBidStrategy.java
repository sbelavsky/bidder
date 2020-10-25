package com.optimax.strategy;

/**
 * always returns zero bid
 */
public class AlwaysZeroBidStrategy implements BidStrategy {
    public int bid() {
        return 0;
    }
}
