package com.optimax.account;

import com.optimax.product.ProductFactory;

public class BidderAccountFactory {
    public static BidderAccount createBidderAccount(int productQuantity, int cash) {
        return new DefaultBidderAccount(ProductFactory.createProduct(productQuantity), cash);
    }
}
