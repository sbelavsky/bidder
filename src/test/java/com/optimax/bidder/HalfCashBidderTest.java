package com.optimax.bidder;

import com.optimax.strategy.HalfCashBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class HalfCashBidderTest extends BaseBidderTest {
    private HalfCashBidder test;

    @BeforeEach
    public void setUp() {
        test = new HalfCashBidder(name, bidderAccount);
    }

    @Test
    void bidStrategy() {
        assertEquals(test.bidStrategy().getClass(), HalfCashBidStrategy.class);
    }

    @Test
    void pay() {
        test.pay(payAmount);
        verify(bidderAccount).payCash(payAmount);
    }

    @Test
    void addProduct() {
        test.addProduct(product);
        verify(bidderAccount).addProduct(product);
    }

    @Test
    void copy() {
        var copy = test.copy();
        assertEquals(test, copy);
    }
}