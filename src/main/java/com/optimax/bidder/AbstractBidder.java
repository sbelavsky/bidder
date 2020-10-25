package com.optimax.bidder;

import com.optimax.Copyable;
import com.optimax.Messages;
import com.optimax.account.BidderAccount;
import com.optimax.exception.DoubleInitializationException;
import com.optimax.exception.UninitializedException;
import com.optimax.product.Product;
import com.optimax.strategy.BidStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Represent base functionality for an auction bidder
 */
public abstract class AbstractBidder implements Bidder, Copyable<AbstractBidder> {

    private final BidderAccount bidderAccount;
    protected final String name;
    private boolean initialized;

    /**
     * Created auction bidder
     *
     * @param name          is a name of the bidder
     * @param bidderAccount is a bidder's account
     */
    public AbstractBidder(String name, BidderAccount bidderAccount) {
        this.bidderAccount = bidderAccount;
        this.name = name;
        init(bidderAccount.getProduct().getQuantity(), bidderAccount.getCash());
    }

    /**
     * copy constructor
     *
     * @param origin is a bidder to copy from
     */
    public AbstractBidder(AbstractBidder origin) {
        this.bidderAccount = origin.bidderAccount.copy();
        this.initialized = origin.initialized;
        this.name = origin.name;
    }

    /**
     * initializes bidder with product's quantity and cash
     *
     * @param quantity the quantity
     * @param cash     the cash limit
     */
    public void init(int quantity, int cash) {
        ensureUninitialized();
        validateCash(cash);
        validateQuantity(quantity);
        initialized = true;
    }

    /**
     * calculates bid based on the provided bidding strategy
     *
     * @return bid amount
     */
    public int placeBid() {
        ensureBidderIsInitialized();
        return bidStrategy().bid();
    }

    /**
     * takes two bids and put them into the output stream
     *
     * @param own   the bid of this bidder
     * @param other the bid of the other bidder
     */
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

    /**
     * used for showing two bidders bids on calling #{bids} method
     *
     * @return stream to write bids to
     */
    protected OutputStream outputStream() {
        return System.out;
    }

    /**
     * extracts cash from the bidder's account
     *
     * @param cash is an amount of money to pay
     * @return updated bidder instance
     */
    public abstract AbstractBidder pay(int cash);

    /**
     * adds product to the bidder's account
     *
     * @param product is an amount of product to add
     * @return updated bidder instance
     */
    public abstract AbstractBidder addProduct(Product product);

    /**
     * @return a copy of the bidder's account
     */
    public BidderAccount getBidderAccount() {
        return bidderAccount.copy();
    }

    /**
     * provides bidder's strategy
     *
     * @return bidder's strategy
     */
    protected abstract BidStrategy bidStrategy();

    private void ensureBidderIsInitialized() {
        if (!initialized) {
            throw new UninitializedException(Messages.BIDDER_UNINITIALIZED);
        }
    }

    private void ensureUninitialized() {
        if (initialized) {
            throw new DoubleInitializationException(Messages.BIDDER_ALREADY_INITIALIZED);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(Messages.PRODUCT_QUANTITY_BELOW_ZERO);
        }
    }

    private void validateCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException(Messages.CASH_BELOW_ZERO);
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
