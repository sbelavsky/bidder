package com.optimax.auction;

import com.optimax.account.BidderAccount;
import com.optimax.account.DefaultBidderAccount;
import com.optimax.bidder.Bidder;
import com.optimax.bidder.BidderFactory;
import com.optimax.participant.AuctionParticipant;
import com.optimax.participant.DefaultAuctionParticipant;
import com.optimax.product.DefaultProduct;
import com.optimax.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TwoPartiesBlindBidAuctionTest {

    @Test
    void whenEqualStrategy_thenTie() {
        Product product = new DefaultProduct(100);
        Bidder firstBidder = BidderFactory.createHalfCashBidder(100, 100);
        BidderAccount firstBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        BidderAccount secondBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);

        Bidder secondBidder = BidderFactory.createHalfCashBidder(100, 100);
        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder, firstBidderAccount);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder, secondBidderAccount);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.TIE, test.run());
    }

    @Test
    void whenFirstIsHalfBidderAndSecondAlwaysZero_thenFirstWins() {
        Product product = new DefaultProduct(100);
        Bidder firstBidder = BidderFactory.createHalfCashBidder(100, 100);
        BidderAccount firstBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        BidderAccount secondBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);

        Bidder secondBidder = BidderFactory.createAlwaysZeroBidder(100, 100);
        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder, firstBidderAccount);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder, secondBidderAccount);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.FIRST_BIDDER_WON, test.run());
    }

    @Test
    void whenFirstIsRandomBidderAndSecondIsHalfBidder_thenSecondWins() {
        Product product = new DefaultProduct(100);
        Bidder firstBidder = BidderFactory.createRandomBidder(100, 100);
        BidderAccount firstBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        BidderAccount secondBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);

        Bidder secondBidder = BidderFactory.createHalfCashBidder(100, 100);
        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder, firstBidderAccount);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder, secondBidderAccount);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.SECOND_BIDDER_WON, test.run());
    }
}