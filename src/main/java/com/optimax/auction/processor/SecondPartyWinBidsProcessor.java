package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

public class SecondPartyWinBidsProcessor extends BidsProcessor<TwoPartiesAuctionResult> {
    public SecondPartyWinBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    @Override
    public Auction<TwoPartiesAuctionResult> handleBids(int firstPartyBid, int secondPartyBid) {
        if (secondPartyBid > firstPartyBid) {
            return new TwoPartiesBlindBidAuction(
                    auctionProduct.extract(productWinningAmount),
                    firstParty.payCash(firstPartyBid),
                    secondParty.payCash(secondPartyBid).addProduct(productWinningAmount));
        }
        return checkNext(firstPartyBid, secondPartyBid);
    }
}
