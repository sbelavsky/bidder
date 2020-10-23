package com.optimax.participant;

import com.optimax.account.BidderAccountFactory;
import com.optimax.bidder.BidderFactory;

public class AuctionParticipantFactory {

    public static AuctionParticipant createHalfCashAuctionParticipant(String name, int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createHalfCashBidder(name,
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }

    public static AuctionParticipant createAlwaysZeroAuctionParticipant(String name, int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createAlwaysZeroBidder(name,
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }

    public static AuctionParticipant createRandomAuctionParticipant(String name, int productQuantity, int cash) {
        return new DefaultAuctionParticipant(
                BidderFactory.createRandomBidder(name,
                        BidderAccountFactory.createBidderAccount(productQuantity, cash)
                ));
    }
}
