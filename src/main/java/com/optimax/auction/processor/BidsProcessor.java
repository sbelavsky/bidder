package com.optimax.auction.processor;

import com.optimax.auction.Auction;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

/**
 * is a responsibility chain of the auction's round winner calculation
 */
public abstract class BidsProcessor {
    protected final AuctionParticipant firstParty;
    protected final AuctionParticipant secondParty;
    protected final Product auctionProduct;
    protected final Product productWinningAmount;
    protected final Product productTieAmount;
    private BidsProcessor next;

    /**
     * bids processor initialization
     *
     * @param auction is an auction to process
     */
    public BidsProcessor(Auction<?> auction) {
        this.firstParty = auction.getParticipants().get(0);
        this.secondParty = auction.getParticipants().get(1);
        this.auctionProduct = auction.getProduct();
        this.productWinningAmount = auctionProduct.copy().setQuantity(2);
        this.productTieAmount = auctionProduct.copy().setQuantity(1);
    }

    /**
     * links processor with a next one
     *
     * @param next is a next processor to check
     * @return provided processor instance
     */
    public BidsProcessor linkWith(BidsProcessor next) {
        this.next = next;
        return next;
    }

    /**
     * compares party bids and distributes product and cash betwenn parties and auction
     *
     * @param firstPartyBid  is a first party bid
     * @param secondPartyBid is a second party bid
     * @return updated auction
     */
    public abstract Auction<?> handleBids(int firstPartyBid, int secondPartyBid);

    /**
     * checks if there is next processor to apply
     *
     * @param firstPartyBid  is a first party bid
     * @param secondPartyBid is a second party bid
     * @return updated auction or null
     */
    protected Auction<?> checkNext(int firstPartyBid, int secondPartyBid) {
        if (next == null) {
            return null;
        }
        return next.handleBids(firstPartyBid, secondPartyBid);
    }
}
