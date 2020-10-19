package com.optimax.bidder;

import com.optimax.strategy.AlwaysZeroBidStrategy;
import com.optimax.strategy.BidStrategy;
import com.optimax.strategy.HalfCashBidStrategy;
import com.optimax.strategy.RandomBidStrategy;

public class BidderFactory {
    public static Bidder createHalfCashBidder(int quantity, int cash) {
        var bidder = new AbstractBidder() {
            @Override
            BidStrategy bidStrategy() {
                return new HalfCashBidStrategy(this);
            }
        };
        bidder.init(quantity, cash);
        return bidder;
    }

    public static Bidder createAlwaysZeroBidder(int quantity, int cash) {
        var bidder = new AbstractBidder() {
            @Override
            BidStrategy bidStrategy() {
                return new AlwaysZeroBidStrategy();
            }
        };
        bidder.init(quantity, cash);
        return bidder;
    }

    public static Bidder createRandomBidder(int quantity, int cash) {
        var bidder = new AbstractBidder() {
            @Override
            BidStrategy bidStrategy() {
                return new RandomBidStrategy(this);
            }
        };
        bidder.init(quantity, cash);
        return bidder;
    }
}
