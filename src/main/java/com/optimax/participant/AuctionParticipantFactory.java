package com.optimax.participant;

import com.optimax.account.BidderAccountFactory;
import com.optimax.bidder.BidderFactory;

public class AuctionParticipantFactory {

    public static AuctionParticipant createHalfCashAuctionParticipant(int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createHalfCashBidder(
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }

    public static AuctionParticipant createAlwaysZeroAuctionParticipant(int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createAlwaysZeroBidder(
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }

    public static AuctionParticipant createRandomAuctionParticipant(int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createRandomBidder(
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }
}
