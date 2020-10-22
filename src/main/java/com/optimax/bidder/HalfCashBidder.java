package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import com.optimax.strategy.BidStrategy;
import com.optimax.strategy.HalfCashBidStrategy;

class HalfCashBidder extends AbstractBidder {

    public HalfCashBidder(BidderAccount bidderAccount) {
        super(bidderAccount);
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
        return new HalfCashBidder(getBidderAccount().payCash(cash));
    }

    @Override
    public AbstractBidder addProduct(Product product) {
        return new HalfCashBidder(getBidderAccount().addProduct(product));
    }

    @Override
    public AbstractBidder copy() {
        return new HalfCashBidder(this);
    }
}
