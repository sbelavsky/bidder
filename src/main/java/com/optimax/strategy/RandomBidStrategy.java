package com.optimax.strategy;

import com.optimax.bidder.AbstractBidder;

import java.util.Random;

public class RandomBidStrategy implements BidStrategy {
    private final AbstractBidder bidder;

    public RandomBidStrategy(AbstractBidder bidder) {
        this.bidder = bidder;
    }

    @Override
    public int bid() {
        var cash = bidder.getBidderAccount().getCash();
        if (cash == 0) {
            return 0;
        }
        return new Random().nextInt(cash + 1);
    }
}
