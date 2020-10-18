package com.optimax.strategy;

import com.optimax.bidder.AbstractBidder;

public class BidStrategyContext<T extends AbstractBidder> {
    private final T own;
    private final T other;

    public BidStrategyContext(T own, T other) {
        this.own = own;
        this.other = other;
    }

    public T getOwn() {
        return own;
    }

    public T getOther() {
        return other;
    }
}
