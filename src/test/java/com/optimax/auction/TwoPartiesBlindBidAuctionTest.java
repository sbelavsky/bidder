package com.optimax.auction;

import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.product.DefaultProduct;
import com.optimax.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.optimax.participant.AuctionParticipantFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPartiesBlindBidAuctionTest {
    private final int cash = 100;
    private final String firstName = "first";
    private final String secondName = "second";
    private final int initialProductQuantity = 0;
    private final Product product = new DefaultProduct(100);

    @Test
    void whenEqualStrategy_thenTie() {
        var firstParty = createHalfCashAuctionParticipant(firstName, initialProductQuantity, cash);
        var secondParty = createHalfCashAuctionParticipant(secondName, initialProductQuantity, cash);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        assertEquals(TwoPartiesAuctionResult.TIE, test.run());
    }

    @Test
    void whenFirstIsHalfBidderAndSecondAlwaysZero_thenFirstWins() {
        var firstParty = createHalfCashAuctionParticipant(firstName, initialProductQuantity, cash);
        var secondParty = createAlwaysZeroAuctionParticipant(secondName, initialProductQuantity, cash);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        assertEquals(TwoPartiesAuctionResult.FIRST_BIDDER_WON, test.run());
    }

    @Test
    void whenFirstIsRandomBidderAndSecondIsHalfBidder_thenSecondWinsMostOfTheTime() {
        var firstParty = createRandomAuctionParticipant(firstName, initialProductQuantity, cash);
        var secondParty = createHalfCashAuctionParticipant(secondName, initialProductQuantity, cash);
        var test = new TwoPartiesBlindBidAuction(product, firstParty, secondParty);
        var successRate = IntStream.range(0, 100)
                .mapToObj(i -> test.run())
                .filter(TwoPartiesAuctionResult.SECOND_BIDDER_WON::equals)
                .count();
        Assertions.assertTrue(successRate > 50);
    }
}