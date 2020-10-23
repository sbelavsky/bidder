package com.optimax.auction;

import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

import java.util.List;

public interface Auction<T extends TwoPartiesAuctionResult> {
    T run();

    List<AuctionParticipant> getParticipants();

    Product getProduct();
}
