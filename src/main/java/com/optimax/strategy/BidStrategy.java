package com.optimax.strategy;

/**
 * Represents a strategy of the bidding
 */
public interface BidStrategy {

    /**
     * @return bid based on the strategy
     */
    int bid();
}
