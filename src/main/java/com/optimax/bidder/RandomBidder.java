package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import com.optimax.strategy.BidStrategy;
import com.optimax.strategy.RandomBidStrategy;

class RandomBidder extends AbstractBidder {

    public RandomBidder(BidderAccount bidderAccount) {
        super(bidderAccount);
    }

    public RandomBidder(AbstractBidder origin) {
        super(origin);
    }

    @Override
    BidStrategy bidStrategy() {
        return new RandomBidStrategy(this);
    }

    @Override
    public AbstractBidder pay(int cash) {
        return new RandomBidder(getBidderAccount().payCash(cash));
    }

    @Override
    public AbstractBidder addProduct(Product product) {
        return new RandomBidder(getBidderAccount().addProduct(product));
    }

    @Override
    public AbstractBidder copy() {
        return new RandomBidder(this);
    }
}
