package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import com.optimax.strategy.AlwaysZeroBidStrategy;
import com.optimax.strategy.BidStrategy;

class AlwaysZeroBidder extends AbstractBidder {

    public AlwaysZeroBidder(String name, BidderAccount bidderAccount) {
        super(name, bidderAccount);
    }

    public AlwaysZeroBidder(AbstractBidder origin) {
        super(origin);
    }

    @Override
    protected BidStrategy bidStrategy() {
        return new AlwaysZeroBidStrategy();
    }

    @Override
    public AbstractBidder pay(int cash) {
        return new AlwaysZeroBidder(name, getBidderAccount().payCash(cash));
    }

    @Override
    public AbstractBidder addProduct(Product product) {
        return new AlwaysZeroBidder(name, getBidderAccount().addProduct(product));
    }

    @Override
    public AbstractBidder copy() {
        return new AlwaysZeroBidder(this);
    }
}
