package com.optimax.bidder;

import com.optimax.strategy.AlwaysZeroBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class AlwaysZeroBidderTest extends BaseBidderTest {
    private AlwaysZeroBidder test;

    @BeforeEach
    public void setUp() {
        test = new AlwaysZeroBidder(name, bidderAccount);
    }

    @Test
    void bidStrategy() {
        assertEquals(test.bidStrategy().getClass(), AlwaysZeroBidStrategy.class);
    }

    @Test
    void pay() {
        var updated = test.pay(payAmount);
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