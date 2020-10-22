package com.optimax.auction;

import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.participant.AuctionParticipantFactory;
import com.optimax.product.DefaultProduct;
import com.optimax.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class TwoPartiesBlindBidAuctionTest {

    @Test
    void whenEqualStrategy_thenTie() {
        var product = new DefaultProduct(100);
        AuctionParticipant firstParty = AuctionParticipantFactory.createHalfCashAuctionParticipant(0, 100);
        AuctionParticipant secondParty = AuctionParticipantFactory.createHalfCashAuctionParticipant(0, 100);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.TIE, test.run());
    }

    @Test
    void whenFirstIsHalfBidderAndSecondAlwaysZero_thenFirstWins() {
        Product product = new DefaultProduct(100);
        AuctionParticipant firstParty = AuctionParticipantFactory.createHalfCashAuctionParticipant(0, 100);
        AuctionParticipant secondParty = AuctionParticipantFactory.createAlwaysZeroAuctionParticipant(0, 100);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        Assertions.assertEquals(TwoPartiesAuctionResult.FIRST_BIDDER_WON, test.run());
    }

    @Test
    void whenFirstIsRandomBidderAndSecondIsHalfBidder_thenSecondWinsMostOfTheTime() {
        Product product = new DefaultProduct(100);
        AuctionParticipant firstParty = AuctionParticipantFactory.createRandomAuctionParticipant(0, 100);
        AuctionParticipant secondParty = AuctionParticipantFactory.createHalfCashAuctionParticipant(0, 100);
        TwoPartiesBlindBidAuction test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        final var successRate = IntStream.range(0, 100)
                .mapToObj(i -> test.run())
                .filter(TwoPartiesAuctionResult.SECOND_BIDDER_WON::equals)
                .count();
        Assertions.assertTrue(successRate > 50);
    }
}