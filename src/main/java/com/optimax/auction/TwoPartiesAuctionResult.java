package com.optimax.auction;

public enum TwoPartiesAuctionResult implements AuctionResult<TwoPartiesAuctionResult> {
    FIRST_BIDDER_WON, SECOND_BIDDER_WON, TIE;

    @Override
    public TwoPartiesAuctionResult result() {
        return this;
    }
}
