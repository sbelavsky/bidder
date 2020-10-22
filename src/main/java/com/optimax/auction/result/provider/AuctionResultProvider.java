package com.optimax.auction.result.provider;

import com.optimax.auction.result.AuctionResult;

public interface AuctionResultProvider<T extends AuctionResult<?>> {
    T provide();
}
