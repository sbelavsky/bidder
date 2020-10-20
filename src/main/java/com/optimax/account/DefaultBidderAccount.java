package com.optimax.account;

import com.optimax.product.Product;

import java.util.Objects;

public class DefaultBidderAccount implements BidderAccount {
    private final Product product;
    private final int cash;

    public DefaultBidderAccount(Product product, int cash) {
        this.product = product;
        this.cash = cash;
    }

    public DefaultBidderAccount(DefaultBidderAccount origin) {
        this.product = origin.product.copy();
        this.cash = origin.cash;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getCash() {
        return cash;
    }

    @Override
    public BidderAccount addProduct(Product product) {
        return new DefaultBidderAccount(this.product.add(product), cash);
    }

    @Override
    public BidderAccount payCash(int amount) {
        return new DefaultBidderAccount(product, cash - amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultBidderAccount that = (DefaultBidderAccount) o;
        return product == that.product &&
                cash == that.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, cash);
    }

    @Override
    public BidderAccount copy() {
        return new DefaultBidderAccount(this);
    }
}
