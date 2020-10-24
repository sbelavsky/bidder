package com.optimax.bidder;

import com.optimax.strategy.RandomBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomBidderTest extends BaseBidderTest {
    private final String name = "test";
    private RandomBidder test;

    @BeforeEach
    public void setUp() {
        test = new RandomBidder(name, bidderAccount);
    }

    @Test
    void bidStrategy() {
        assertEquals(test.bidStrategy().getClass(), RandomBidStrategy.class);
    }

    @Test
    void pay() {
        var updated = test.pay(50);
        assertEquals(new RandomBidder(name, updatedCashBidderAccount), updated);

    }

    @Test
    void addProduct() {
        var updated = test.addProduct(product);
        assertEquals(new RandomBidder(name, updatedProductBidderAccount), updated);
    }

    @Test
    void copy() {
        var copy = test.copy();
        assertEquals(test, copy);
    }
}