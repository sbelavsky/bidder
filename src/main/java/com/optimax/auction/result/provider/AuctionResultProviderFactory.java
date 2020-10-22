package com.optimax.auction.result.provider;

import com.optimax.auction.Auction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

public class AuctionResultProviderFactory {
    public static AuctionResultProvider<TwoPartiesAuctionResult> createAuctionResultProvider(Auction<TwoPartiesAuctionResult> auction) {
        return new DefaultAuctionResultProvider(auction);
    }
}
