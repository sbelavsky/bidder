package com.optimax.bidder;

import com.optimax.strategy.BidStrategy;
import com.optimax.strategy.HalfCashBidStrategy;

public class BidderFactory {
    public static Bidder createHalfCashBidder() {
        return new AbstractBidder() {
            @Override
            BidStrategy bidStrategy() {
                return new HalfCashBidStrategy(this);
            }
        };
    }
}
