package com.optimax.strategy;

import com.optimax.bidder.AbstractBidder;

public class HalfCashBidStrategy implements BidStrategy {

    private final AbstractBidder bidder;

    public HalfCashBidStrategy(AbstractBidder bidder) {
        this.bidder = bidder;
    }

    @Override
    public int bid() {
        var cash = bidder.getBidderAccount().getCash();
        if (cash == 0 || cash == 1) {
            return 0;
        }
        return cash / 2;
    }
}
