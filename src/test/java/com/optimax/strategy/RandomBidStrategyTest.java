package com.optimax.strategy;

import com.optimax.account.BidderAccount;
import com.optimax.bidder.AbstractBidder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomBidStrategyTest {

    private final AbstractBidder abstractBidder = mock(AbstractBidder.class);
    private final BidderAccount bidderAccount = mock(BidderAccount.class);

    @Test
    void bid() {
        when(bidderAccount.getCash()).thenReturn(100);
        when(abstractBidder.getBidderAccount()).thenReturn(bidderAccount);
        var test = new RandomBidStrategy(abstractBidder);
        final var bid = test.bid();
        assertTrue(0 <= bid && bid <= 100);
    }
}