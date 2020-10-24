package com.optimax.bidder;

import com.optimax.strategy.HalfCashBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HalfCashBidderTest extends BaseBidderTest {
    private final String name = "test";
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
        var updated = test.pay(50);
        assertEquals(new HalfCashBidder(name, updatedCashBidderAccount), updated);

    }

    @Test
    void addProduct() {
        var updated = test.addProduct(product);
        assertEquals(new HalfCashBidder(name, updatedProductBidderAccount), updated);
    }

    @Test
    void copy() {
        var copy = test.copy();
        assertEquals(test, copy);
    }
}