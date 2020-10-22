package com.optimax.participant;

import com.optimax.product.Product;

import java.util.Comparator;

public class AuctionParticipantComparatorFactory {
    public static Comparator<AuctionParticipant> twoPartiesBlindBidComparator() {
        return Comparator.comparing(AuctionParticipant::getProduct, Comparator.comparingInt(Product::getQuantity))
                .thenComparing(AuctionParticipant::getCash);
    }
}
