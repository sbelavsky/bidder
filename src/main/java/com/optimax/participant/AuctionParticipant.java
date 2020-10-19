package com.optimax.participant;

import com.optimax.Copyable;
import com.optimax.product.Product;

public interface AuctionParticipant extends Copyable<AuctionParticipant> {
    int bid();

    int getCash();

    Product getProduct();

    AuctionParticipant payCash(int amount);

    AuctionParticipant addProduct(Product product);
}
