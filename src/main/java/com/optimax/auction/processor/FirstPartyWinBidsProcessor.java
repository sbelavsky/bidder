package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

public class FirstPartyWinBidsProcessor extends BidsProcessor<TwoPartiesAuctionResult> {
    public FirstPartyWinBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    @Override
    public Auction<TwoPartiesAuctionResult> handleBids(int firstPartyBid, int secondPartyBid) {
        if (firstPartyBid > secondPartyBid) {
            return new TwoPartiesBlindBidAuction(
                    auctionProduct.extract(productWinningAmount),
                    firstParty.payCash(firstPartyBid).addProduct(productWinningAmount),
                    secondParty.payCash(secondPartyBid));
        }
        return checkNext(firstPartyBid, secondPartyBid);
    }
}
