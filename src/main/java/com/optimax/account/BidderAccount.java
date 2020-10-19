package com.optimax.account;

import com.optimax.product.Product;

public interface BidderAccount {
    Product getProduct();

    int getCash();

    BidderAccount addProduct(Product product);

    BidderAccount payCash(int amount);
}
