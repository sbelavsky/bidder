package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

/**
 * Represents processor that handles win of the first party
 */
public class FirstPartyWinBidsProcessor extends BidsProcessor {
    public FirstPartyWinBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    /**
     * Checks if first party bid was greater than the second's one
     * and goes for the next processor if not
     *
     * @param firstPartyBid  is a first party bid
     * @param secondPartyBid is a second party bid
     * @return update auction instance
     */
    @Override
    public Auction<?> handleBids(int firstPartyBid, int secondPartyBid) {
        if (firstPartyBid > secondPartyBid) {
            return new TwoPartiesBlindBidAuction(
                    auctionProduct.extract(productWinningAmount),
                    firstParty.payCash(firstPartyBid).addProduct(productWinningAmount),
                    secondParty.payCash(secondPartyBid));
        }
        return checkNext(firstPartyBid, secondPartyBid);
    }
}
