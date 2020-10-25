package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

/**
 * Represents processor that handles tie
 */
public class TieBidsProcessor extends BidsProcessor {
    public TieBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    /**
     * Checks if second party bid was equal to the first's one
     * and goes for the next processor if not
     *
     * @param firstPartyBid  is a first party bid
     * @param secondPartyBid is a second party bid
     * @return update auction instance
     */
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
