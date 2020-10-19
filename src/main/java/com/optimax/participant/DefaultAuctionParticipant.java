package com.optimax.participant;

import com.optimax.account.BidderAccount;
import com.optimax.bidder.Bidder;
import com.optimax.product.Product;

import java.util.Objects;

public class DefaultAuctionParticipant implements AuctionParticipant {

    private final Bidder bidder;
    private final BidderAccount bidderAccount;

    public DefaultAuctionParticipant(Bidder bidder, BidderAccount bidderAccount) {
        this.bidder = bidder;
        this.bidderAccount = bidderAccount;
    }

    private DefaultAuctionParticipant(DefaultAuctionParticipant target) {
        this.bidder = target.bidder;
        this.bidderAccount = target.bidderAccount;
    }

    public int bid() {
        return bidder.placeBid();
    }

    public int getCash() {
        return bidderAccount.getCash();
    }

    @Override
    public Product getProduct() {
        return bidderAccount.getProduct();
    }

    @Override
    public AuctionParticipant payCash(int amount) {
        return new DefaultAuctionParticipant(bidder, bidderAccount.payCash(amount));
    }

    @Override
    public AuctionParticipant addProduct(Product product) {
        return new DefaultAuctionParticipant(bidder, bidderAccount.addProduct(product));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultAuctionParticipant that = (DefaultAuctionParticipant) o;
        return Objects.equals(bidder, that.bidder) &&
                Objects.equals(bidderAccount, that.bidderAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidder, bidderAccount);
    }

    @Override
    public AuctionParticipant copy() {
        return new DefaultAuctionParticipant(this);
    }
}
