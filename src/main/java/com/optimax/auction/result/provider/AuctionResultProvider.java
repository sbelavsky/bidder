package com.optimax.auction.result.provider;

/**
 * Provides final auction's result
 *
 * @param <T> is a type of result
 */
public interface AuctionResultProvider<T> {
    T provide();
}
