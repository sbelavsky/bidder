package com.optimax.auction;

import com.optimax.auction.processor.BidsProcessorFactory;
import com.optimax.auction.result.TwoPartiesAuctionResult;
import com.optimax.auction.result.provider.AuctionResultProviderFactory;
import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TwoPartiesBlindBidAuction implements Auction<TwoPartiesAuctionResult> {

    private final AuctionParticipant firstParty;
    private final AuctionParticipant secondParty;
    private final Product product;

    public TwoPartiesBlindBidAuction(Product product, AuctionParticipant firstParty, AuctionParticipant secondParty) {
        this.firstParty = firstParty;
        this.secondParty = secondParty;
        this.product = product;
    }

    @Override
    public TwoPartiesAuctionResult run() {
        if (isAuctionFinished()) {
            return result();
        }
        return handleBids(putBidsSimultaneously());
    }

    private int[] putBidsSimultaneously() {
        final var firstBidCompletableFuture = CompletableFuture.supplyAsync(firstParty::bid);
        final var secondBidCompletableFuture = CompletableFuture.supplyAsync(secondParty::bid);
        CompletableFuture.allOf(firstBidCompletableFuture, secondBidCompletableFuture).join();
        var firstPartyBid = firstBidCompletableFuture.join();
        var secondPartyBid = secondBidCompletableFuture.join();
        return new int[]{firstPartyBid, secondPartyBid};
    }

    private boolean isAuctionFinished() {
        return product.isEmpty();
    }

    private TwoPartiesAuctionResult result() {
        return AuctionResultProviderFactory
                .createAuctionResultProvider(this)
                .provide();
    }

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
