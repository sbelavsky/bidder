package com.optimax.bidder;

import com.optimax.Configuration;
import com.optimax.Copyable;
import com.optimax.account.BidderAccount;
import com.optimax.exception.DoubleInitializationException;
import com.optimax.exception.UninitializedException;
import com.optimax.product.Product;
import com.optimax.strategy.BidStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public abstract class AbstractBidder implements Bidder, Copyable<AbstractBidder> {

    private final BidderAccount bidderAccount;
    protected final String name;
    private boolean initialized;

    public AbstractBidder(String name, BidderAccount bidderAccount) {
        this.bidderAccount = bidderAccount;
        this.name = name;
        init(bidderAccount.getProduct().getQuantity(), bidderAccount.getCash());
    }

    public AbstractBidder(AbstractBidder origin) {
        this.bidderAccount = origin.bidderAccount.copy();
        this.initialized = origin.initialized;
        this.name = origin.name;
    }

    public void init(int quantity, int cash) {
        ensureUninitialized();
        validateCash(cash);
        validateQuantity(quantity);
        initialized = true;
    }

    public int placeBid() {
        ensureBidderIsInitialized();
        return bidStrategy().bid();
    }

    public void bids(int own, int other) {
        ensureBidderIsInitialized();
        var output = String.format("[%s] Own bid: %d, other bid: %d \n", name, own, other);
        var stream = outputStream();
        try {
            stream.write(output.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected OutputStream outputStream() {
        return System.out;
    }

    public abstract AbstractBidder pay(int cash);

    public abstract AbstractBidder addProduct(Product product);

    public BidderAccount getBidderAccount() {
        return bidderAccount.copy();
    }

    protected abstract BidStrategy bidStrategy();

    private void ensureBidderIsInitialized() {
        if (!initialized) {
            throw new UninitializedException(Configuration.BIDDER_UNINITIALIZED);
        }
    }

    private void ensureUninitialized() {
        if (initialized) {
            throw new DoubleInitializationException(Configuration.BIDDER_ALREADY_INITIALIZED);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(Configuration.PRODUCT_QUANTITY_BELOW_ZERO);
        }
    }

    private void validateCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException(Configuration.CASH_BELOW_ZERO);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBidder that = (AbstractBidder) o;
        return initialized == that.initialized &&
                Objects.equals(bidderAccount, that.bidderAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidderAccount, initialized);
    }
}
