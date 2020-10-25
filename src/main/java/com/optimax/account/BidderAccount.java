package com.optimax.account;

import com.optimax.Copyable;
import com.optimax.product.Product;

/**
 * Represents a bidder's account which encapsulates its product and cash operations
 */
public interface BidderAccount extends Copyable<BidderAccount> {

    /**
     * @return account product
     */
    Product getProduct();

    /**
     * @return amount of account's cash
     */
    int getCash();

    /**
     * @param product is a product being added
     * @return updated bidder's account
     */
    BidderAccount addProduct(Product product);

    /**
     * @param amount is an amount to pay
     * @return updated bidder's account
     */
    BidderAccount payCash(int amount);
}
