package com.optimax.participant;

import com.optimax.account.BidderAccount;
import com.optimax.bidder.AbstractBidder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DefaultAuctionParticipantTest {

    private final BidderAccount bidderAccount = mock(BidderAccount.class);
    private final AbstractBidder abstractBidder = mock(AbstractBidder.class);
    private DefaultAuctionParticipant test;

    @BeforeEach
    void setUp() {
        when(abstractBidder.getBidderAccount()).thenReturn(bidderAccount);
        test = new DefaultAuctionParticipant(abstractBidder);
    }

    @Test
    void bid() {
        test.bid();
        verify(abstractBidder).placeBid();
    }

    @Test
    void getCash() {
        when(abstractBidder.getBidderAccount()).thenReturn(bidderAccount);
        test.getCash();
        verify(bidderAccount).getCash();
    }

    @Test
    void supplyBids() {
        test.supplyBids(10, 20);
        verify(abstractBidder).bids(10, 20);
    }

    @Test
    void getProduct() {
        test.getProduct();
        verify(bidderAccount).getProduct();
    }

    @Test
    void payCash() {
        test.payCash(100);
        verify(abstractBidder).pay(100);

    }

    @Test
    void addProduct() {
        test.addProduct(null);
        verify(abstractBidder).addProduct(null);
    }

    @Test
    void testEquals() {
        assertEquals(new DefaultAuctionParticipant(abstractBidder), test);
    }

    @Test
    void testHashCode() {
        assertEquals(new DefaultAuctionParticipant(abstractBidder).hashCode(), test.hashCode());
    }

    @Test
    void copy() {
        when(abstractBidder.copy()).thenReturn(abstractBidder);
        assertEquals(test, test.copy());
    }
}