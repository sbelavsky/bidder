package com.optimax.auction;

import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipantFactory;
import com.optimax.product.DefaultProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class TwoPartiesBlindBidAuctionTest {

    @Test
    void whenEqualStrategy_thenTie() {
        var product = new DefaultProduct(100);
        var firstParty = AuctionParticipantFactory.createHalfCashAuctionParticipant("first", 0, 100);
        var secondParty = AuctionParticipantFactory.createHalfCashAuctionParticipant("second", 0, 100);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.TIE, test.run());
    }

    @Test
    void whenFirstIsHalfBidderAndSecondAlwaysZero_thenFirstWins() {
        var product = new DefaultProduct(100);
        var firstParty = AuctionParticipantFactory.createHalfCashAuctionParticipant("first", 0, 100);
        var secondParty = AuctionParticipantFactory.createAlwaysZeroAuctionParticipant("second", 0, 100);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.FIRST_BIDDER_WON, test.run());
    }

    @Test
    void whenFirstIsRandomBidderAndSecondIsHalfBidder_thenSecondWinsMostOfTheTime() {
        var product = new DefaultProduct(100);
        var firstParty = AuctionParticipantFactory.createRandomAuctionParticipant("first", 0, 100);
        var secondParty = AuctionParticipantFactory.createHalfCashAuctionParticipant("second", 0, 100);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        var successRate = IntStream.range(0, 100)
                .mapToObj(i -> test.run())
                .filter(TwoPartiesAuctionResult.SECOND_BIDDER_WON::equals)
                .count();
        Assertions.assertTrue(successRate > 50);
    }
}