package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

public class BidsProcessorFactory {
    public static BidsProcessor createBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        final var rootProcessor = new TieBidsProcessor(auction);
        rootProcessor.linkWith(new FirstPartyWinBidsProcessor(auction)).linkWith(new SecondPartyWinBidsProcessor(auction));
        return rootProcessor;
    }
}
