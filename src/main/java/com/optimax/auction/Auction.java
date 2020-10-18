package com.optimax.auction;

public interface Auction<T> {
    AuctionResult<T> run();
}
