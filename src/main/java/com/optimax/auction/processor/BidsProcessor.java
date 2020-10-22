package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.auction.result.AuctionResult;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

public abstract class BidsProcessor<T extends AuctionResult<?>> {
    protected final AuctionParticipant firstParty;
    protected final AuctionParticipant secondParty;
    protected final Product auctionProduct;
    protected final Product productWinningAmount;
    protected final Product productTieAmount;
    private BidsProcessor<T> next;

    public BidsProcessor(Auction<T> auction) {
        this.firstParty = auction.getParticipants().get(0);
        this.secondParty = auction.getParticipants().get(1);
        this.auctionProduct = auction.getProduct();
        this.productWinningAmount = auctionProduct.copy().setQuantity(2);
        this.productTieAmount = auctionProduct.copy().setQuantity(1);
    }

    public BidsProcessor<T> linkWith(BidsProcessor<T> next) {
        this.next = next;
        return next;
    }

    public abstract Auction<T> handleBids(int firstPartyBid, int secondPartyBid);

    protected Auction<T> checkNext(int firstPartyBid, int secondPartyBid) {
        if (next == null) {
            return null;
        }
        return next.handleBids(firstPartyBid, secondPartyBid);
    }
}
