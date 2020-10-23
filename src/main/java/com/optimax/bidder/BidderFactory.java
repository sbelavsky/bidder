package com.optimax.bidder;

import com.optimax.account.BidderAccount;

public class BidderFactory {

    public static AbstractBidder createHalfCashBidder(String name, BidderAccount bidderAccount) {
        return new HalfCashBidder(name, bidderAccount);
    }

    public static AbstractBidder createAlwaysZeroBidder(String name, BidderAccount bidderAccount) {
        return new AlwaysZeroBidder(name, bidderAccount);
    }

    public static AbstractBidder createRandomBidder(String name, BidderAccount bidderAccount) {
        return new RandomBidder(name, bidderAccount);
    }
}
