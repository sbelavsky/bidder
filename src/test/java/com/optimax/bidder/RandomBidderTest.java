package com.optimax.bidder;

import com.optimax.strategy.RandomBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class RandomBidderTest extends BaseBidderTest {
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