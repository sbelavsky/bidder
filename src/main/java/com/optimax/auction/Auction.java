package com.optimax.auction;

import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

import java.util.List;

/**
 * Represents basic auction behaviour
 *
 * @param <T> is an auction's result type
 */
public interface Auction<T extends TwoPartiesAuctionResult> {
    /**
     * executes auction round
     *
     * @return auction result when completed
     */
    T run();

    /**
     * @return a list of auction's participants
     */
    List<AuctionParticipant> getParticipants();

    /**
     * @return amount of auction's product
     */
    Product getProduct();
}
