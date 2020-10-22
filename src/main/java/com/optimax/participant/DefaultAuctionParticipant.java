package com.optimax.participant;

import com.optimax.bidder.AbstractBidder;
import com.optimax.product.Product;

import java.util.Objects;

public class DefaultAuctionParticipant implements AuctionParticipant {

    private final AbstractBidder bidder;

    public DefaultAuctionParticipant(AbstractBidder bidder) {
        this.bidder = bidder;
    }

    private DefaultAuctionParticipant(DefaultAuctionParticipant target) {
        this.bidder = target.bidder.copy();
    }

    public int bid() {
        return bidder.placeBid();
    }

    public int getCash() {
        return bidder.getBidderAccount().getCash();
    }

    public void supplyBids(int own, int other) {
        bidder.bids(own, other);
    }

    @Override
    public Product getProduct() {
        return bidder.getBidderAccount().getProduct();
    }

    @Override
    public AuctionParticipant payCash(int amount) {
        return new DefaultAuctionParticipant(bidder.pay(amount));
    }

    @Override
    public AuctionParticipant addProduct(Product product) {
        return new DefaultAuctionParticipant(bidder.addProduct(product));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultAuctionParticipant that = (DefaultAuctionParticipant) o;
        return Objects.equals(bidder, that.bidder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidder);
    }

    @Override
    public AuctionParticipant copy() {
        return new DefaultAuctionParticipant(this);
    }
}
