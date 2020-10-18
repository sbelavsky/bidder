package com.optimax.account;

public class DefaultBidderAccount implements BidderAccount {
    private final int productQuantity;
    private final int cash;

    public DefaultBidderAccount(int productQuantity, int cash) {
        this.productQuantity = productQuantity;
        this.cash = cash;
    }

    @Override
    public int getProductQuantity() {
        return productQuantity;
    }

    @Override
    public int getCash() {
        return cash;
    }
}
