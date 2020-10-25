package com.optimax.strategy;

import com.optimax.bidder.AbstractBidder;

/**
 * returns half of the bidder's cash
 */
public class HalfCashBidStrategy implements BidStrategy {

    private final AbstractBidder bidder;

    public HalfCashBidStrategy(AbstractBidder bidder) {
        this.bidder = bidder;
    }

    @Override
    public int bid() {
        var cash = bidder.getBidderAccount().getCash();
        return cash / 2;
    }
}
