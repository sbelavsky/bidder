package com.optimax.participant;

import com.optimax.product.Product;

import java.util.Comparator;

public class AuctionParticipantComparatorFactory {
    /**
     * compares auction participants based on its product quantity and cash
     *
     * @return comparator instance
     */
    public static Comparator<AuctionParticipant> twoPartiesBlindBidComparator() {
        return Comparator.comparing(AuctionParticipant::getProduct, Comparator.comparingInt(Product::getQuantity))
                .thenComparing(AuctionParticipant::getCash);
    }
}
