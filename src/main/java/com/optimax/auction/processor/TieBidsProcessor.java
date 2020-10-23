package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

public class TieBidsProcessor extends BidsProcessor {
    public TieBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    @Override
    public Auction<?> handleBids(int firstPartyBid, int secondPartyBid) {
        if (firstPartyBid == secondPartyBid) {
            return new TwoPartiesBlindBidAuction(
                    auctionProduct.extract(productTieAmount),
                    secondParty.payCash(firstPartyBid).addProduct(productTieAmount),
                    firstParty.payCash(secondPartyBid).addProduct(productTieAmount));
        }
        return checkNext(firstPartyBid, secondPartyBid);
    }
}
