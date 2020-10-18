package com.optimax.strategy;

public class NoOpBidStrategy implements BidStrategy {
    public int bid() {
        throw new UnsupportedOperationException();
    }
}
