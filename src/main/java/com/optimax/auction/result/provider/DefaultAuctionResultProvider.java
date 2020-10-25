package com.optimax.auction.result.provider;

import com.optimax.Messages;
import com.optimax.auction.Auction;
import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.participant.AuctionParticipantComparatorFactory;

/**
 * Provides result of the two parties auction
 */
public class DefaultAuctionResultProvider implements AuctionResultProvider<TwoPartiesAuctionResult> {

    private final AuctionParticipant firstParty;
    private final AuctionParticipant secondParty;

    /**
     * Creates auction provider
     *
     * @param auction is an auction to provide results from
     */
    public DefaultAuctionResultProvider(Auction<TwoPartiesAuctionResult> auction) {
        this.firstParty = auction.getParticipants().get(0);
        this.secondParty = auction.getParticipants().get(1);
    }

    /**
     * Compares two parties and make a decision regarding auction's result
     *
     * @return two parties auction result or throws an exception
     */
    @Override
    public TwoPartiesAuctionResult provide() {
        final var compare = AuctionParticipantComparatorFactory.twoPartiesBlindBidComparator()
                .compare(firstParty, secondParty);
        switch (compare) {
            case 0: {
                return TwoPartiesAuctionResult.TIE;
            }
            case 1: {
                return TwoPartiesAuctionResult.FIRST_BIDDER_WON;
            }
            case -1: {
                return TwoPartiesAuctionResult.SECOND_BIDDER_WON;
            }
        }
        throw new RuntimeException(Messages.UNEXPECTED_COMPARISON);
    }
}
