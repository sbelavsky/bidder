package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import com.optimax.strategy.BidStrategy;
import com.optimax.strategy.HalfCashBidStrategy;

/**
 * uses always half of the bidder's cash bid strategy
 */
class HalfCashBidder extends AbstractBidder {

    public HalfCashBidder(String name, BidderAccount bidderAccount) {
        super(name, bidderAccount);
    }

    public HalfCashBidder(AbstractBidder origin) {
        super(origin);
    }

    @Override
    protected BidStrategy bidStrategy() {
        return new HalfCashBidStrategy(this);
    }

    @Override
    public AbstractBidder pay(int cash) {
        return new HalfCashBidder(name, getBidderAccount().payCash(cash));
    }

    @Override
    public AbstractBidder addProduct(Product product) {
        return new HalfCashBidder(name, getBidderAccount().addProduct(product));
    }

    @Override
    public AbstractBidder copy() {
        return new HalfCashBidder(this);
    }
}
