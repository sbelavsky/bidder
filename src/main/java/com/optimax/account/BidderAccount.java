package com.optimax.account;

import com.optimax.Copyable;
import com.optimax.product.Product;

public interface BidderAccount extends Copyable<BidderAccount> {
    Product getProduct();

    int getCash();

    BidderAccount addProduct(Product product);

    BidderAccount payCash(int amount);
}
