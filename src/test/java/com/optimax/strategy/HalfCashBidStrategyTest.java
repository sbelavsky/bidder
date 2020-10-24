package com.optimax.strategy;

import com.optimax.account.BidderAccount;
import com.optimax.bidder.AbstractBidder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HalfCashBidStrategyTest {

    private final AbstractBidder abstractBidder = mock(AbstractBidder.class);
    private final BidderAccount bidderAccount = mock(BidderAccount.class);

    @Test
    void bid() {
        when(bidderAccount.getCash()).thenReturn(100);
        when(abstractBidder.getBidderAccount()).thenReturn(bidderAccount);
        var test = new HalfCashBidStrategy(abstractBidder);
        assertEquals(50, test.bid());
    }
}