package com.optimax.auction;

import com.optimax.account.BidderAccount;
import com.optimax.account.DefaultBidderAccount;
import com.optimax.bidder.AbstractBidder;
import com.optimax.bidder.BidderFactory;
import com.optimax.participant.AuctionParticipant;
import com.optimax.participant.DefaultAuctionParticipant;
import com.optimax.product.DefaultProduct;
import com.optimax.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TwoPartiesBlindBidAuctionTest {

    @Test
    void whenEqualStrategy_thenTie() {
        Product product = new DefaultProduct(100);
        final var defaultBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        AbstractBidder firstBidder = BidderFactory.createHalfCashBidder(defaultBidderAccount);
        AbstractBidder secondBidder = BidderFactory.createHalfCashBidder(defaultBidderAccount);
        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.TIE, test.run());
    }

    @Test
    void whenFirstIsHalfBidderAndSecondAlwaysZero_thenFirstWins() {
        Product product = new DefaultProduct(100);
        BidderAccount defaultBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        AbstractBidder firstBidder = BidderFactory.createHalfCashBidder(defaultBidderAccount);
        AbstractBidder secondBidder = BidderFactory.createAlwaysZeroBidder(defaultBidderAccount);

        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.FIRST_BIDDER_WON, test.run());
    }

    @Test
    void whenFirstIsRandomBidderAndSecondIsHalfBidder_thenSecondWinsMostOfTheTime() {
        Product product = new DefaultProduct(100);
        BidderAccount defaultBidderAccount = new DefaultBidderAccount(new DefaultProduct(0), 100);
        AbstractBidder firstBidder = BidderFactory.createRandomBidder(defaultBidderAccount);
        AbstractBidder secondBidder = BidderFactory.createHalfCashBidder(defaultBidderAccount);

        AuctionParticipant firstParty = new DefaultAuctionParticipant(firstBidder);
        AuctionParticipant secondParty = new DefaultAuctionParticipant(secondBidder);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        var stats = new ArrayList<AuctionResult<TwoPartiesAuctionResult>>();
        for (int i = 0; i < 100; i++) {
            stats.add(test.run());
        }
        Assertions.assertTrue(stats.stream()
                .filter(TwoPartiesAuctionResult.SECOND_BIDDER_WON::equals)
                .count() > 50);
    }
}