package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.TwoPartiesBlindBidAuction;
import com.optimax.auction.result.TwoPartiesAuctionResult;

/**
 * Represents processor that handles win of the second party
 */
public class SecondPartyWinBidsProcessor extends BidsProcessor {
    public SecondPartyWinBidsProcessor(Auction<TwoPartiesAuctionResult> auction) {
        super(auction);
    }

    /**
     * Checks if second party bid was greater than the first's one
     * and goes for the next processor if not
     *
     * @param firstPartyBid  is a first party bid
     * @param secondPartyBid is a second party bid
     * @return update auction instance
     */
    @Override
    public Auction<?> handleBids(int firstPartyBid, int secondPartyBid) {
        if (secondPartyBid > firstPartyBid) {
            return new TwoPartiesBlindBidAuction(
                    auctionProduct.extract(productWinningAmount),
                    firstParty.payCash(firstPartyBid),
                    secondParty.payCash(secondPartyBid).addProduct(productWinningAmount));
        }
        return checkNext(firstPartyBid, secondPartyBid);
    }
}
