package com.optimax.bidder;

import com.optimax.exception.UninitializedException;
import com.optimax.strategy.BidStrategy;

import java.util.Objects;

public abstract class AbstractBidder implements Bidder {

    private int quantity;
    private int cash;
    private boolean initialized;

    public void init(int quantity, int cash) {
        validateCash(cash);
        validateQuantity(quantity);

        this.quantity = quantity;
        this.cash = cash;
        initialized = true;
    }

    public int placeBid() {
        ensureBidderIsInitialized();
        return bidStrategy().bid();
    }

    public void bids(int own, int other) {
        ensureBidderIsInitialized();
        //todo display
    }

    private void ensureBidderIsInitialized() {
        if (!initialized) {
            throw new UninitializedException("Bidder is uninitialized. Call init() method first");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("product quantity cannot be below zero");
        }
    }

    private void validateCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("cash cannot be below zero");
        }
    }

    abstract BidStrategy bidStrategy();

    public int getQuantity() {
        return quantity;
    }

    public int getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBidder that = (AbstractBidder) o;
        return quantity == that.quantity &&
                cash == that.cash &&
                initialized == that.initialized;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, cash, initialized);
    }
}
