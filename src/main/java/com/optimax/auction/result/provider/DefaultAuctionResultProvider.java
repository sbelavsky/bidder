package com.optimax.auction.result.provider;

import com.optimax.Configuration;
import com.optimax.auction.Auction;
import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.participant.AuctionParticipantComparatorFactory;

public class DefaultAuctionResultProvider implements AuctionResultProvider<TwoPartiesAuctionResult> {

    private final AuctionParticipant firstParty;
    private final AuctionParticipant secondParty;

    public DefaultAuctionResultProvider(Auction<TwoPartiesAuctionResult> auction) {
        this.firstParty = auction.getParticipants().get(0);
        this.secondParty = auction.getParticipants().get(1);
    }

    @Override
    public TwoPartiesAuctionResult provide() {
        final var compare = AuctionParticipantComparatorFactory.twoPartiesBlindBidComparator().compare(firstParty, secondParty);
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
        throw new RuntimeException(Configuration.UNEXPECTED_COMPARISON);
    }
}
