package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import com.optimax.strategy.AlwaysZeroBidStrategy;
import com.optimax.strategy.BidStrategy;

class AlwaysZeroBidder extends AbstractBidder {

    public AlwaysZeroBidder(BidderAccount bidderAccount) {
        super(bidderAccount);
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
        return new AlwaysZeroBidder(getBidderAccount().payCash(cash));
    }

    @Override
    public AbstractBidder addProduct(Product product) {
        return new AlwaysZeroBidder(getBidderAccount().addProduct(product));
    }

    @Override
    public AbstractBidder copy() {
        return new AlwaysZeroBidder(this);
    }
}
