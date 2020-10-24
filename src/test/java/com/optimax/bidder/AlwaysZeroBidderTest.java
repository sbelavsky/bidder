package com.optimax.bidder;

import com.optimax.strategy.AlwaysZeroBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlwaysZeroBidderTest extends BaseBidderTest {
    private final String name = "test";
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
        var updated = test.pay(50);
        assertEquals(new AlwaysZeroBidder(name, updatedCashBidderAccount), updated);

    }

    @Test
    void addProduct() {
        var updated = test.addProduct(product);
        assertEquals(new AlwaysZeroBidder(name, updatedProductBidderAccount), updated);
    }

    @Test
    void copy() {
        var copy = test.copy();
        assertEquals(test, copy);
    }
}