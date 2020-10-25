package com.optimax.auction;

import com.optimax.auction.processor.BidsProcessorFactory;
import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.auction.result.provider.AuctionResultProviderFactory;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Represents auction of two parties putting their blind bids simultaneously,
 * being notified about each others bids afterward and finishes
 * when auction is run out of product to trade
 */
public class TwoPartiesBlindBidAuction implements Auction<TwoPartiesAuctionResult> {

    private final AuctionParticipant firstParty;
    private final AuctionParticipant secondParty;
    private final Product product;

    /**
     * Creates auction
     *
     * @param product     initial auction's product
     * @param firstParty  first auction's participant
     * @param secondParty second auction's participant
     */
    public TwoPartiesBlindBidAuction(Product product, AuctionParticipant firstParty, AuctionParticipant secondParty) {
        this.firstParty = firstParty;
        this.secondParty = secondParty;
        this.product = product;
    }

    /**
     * executes auction round
     *
     * @return auction result
     */
    @Override
    public TwoPartiesAuctionResult run() {
        if (isAuctionFinished()) {
            return result();
        }
        return handleBids(putBidsSimultaneously());
    }

    /**
     * simulates bidding of two parties simultaneously
     *
     * @return array of bids
     */
    private int[] putBidsSimultaneously() {
        final var firstBidCompletableFuture = CompletableFuture.supplyAsync(firstParty::bid);
        final var secondBidCompletableFuture = CompletableFuture.supplyAsync(secondParty::bid);
        CompletableFuture.allOf(firstBidCompletableFuture, secondBidCompletableFuture).join();
        var firstPartyBid = firstBidCompletableFuture.join();
        var secondPartyBid = secondBidCompletableFuture.join();
        return new int[]{firstPartyBid, secondPartyBid};
    }

    /**
     * auction is considered as finished when it's run out of product to trade
     */
    private boolean isAuctionFinished() {
        return product.isEmpty();
    }

    /**
     * @return auction's winner
     */
    private TwoPartiesAuctionResult result() {
        return AuctionResultProviderFactory
                .createAuctionResultProvider(this)
                .provide();
    }

    /**
     * processes parties bids
     *
     * @param bids is an array of bids
     * @return auction round winner
     */
    private TwoPartiesAuctionResult handleBids(int[] bids) {
        final var firstPartyBid = bids[0];
        final var secondPartyBid = bids[1];
        firstParty.supplyBids(firstPartyBid, secondPartyBid);
        secondParty.supplyBids(secondPartyBid, firstPartyBid);

        return BidsProcessorFactory.createBidsProcessor(this)
                .handleBids(firstPartyBid, secondPartyBid)
                .run();
    }

    @Override
    public List<AuctionParticipant> getParticipants() {
        return Arrays.asList(firstParty, secondParty);
    }

    @Override
    public Product getProduct() {
        return product.copy();
    }
}
