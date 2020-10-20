package com.optimax.bidder;

import com.optimax.account.BidderAccount;

public class BidderFactory {

    public static AbstractBidder createHalfCashBidder(BidderAccount bidderAccount) {
        return new HalfCashBidder(bidderAccount);
    }

    public static AbstractBidder createAlwaysZeroBidder(BidderAccount bidderAccount) {
        return new AlwaysZeroBidder(bidderAccount);
    }

    public static AbstractBidder createRandomBidder(BidderAccount bidderAccount) {
        return new RandomBidder(bidderAccount);
    }
}
