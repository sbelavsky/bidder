package com.optimax.auction;

import com.optimax.participant.AuctionParticipant;
import com.optimax.product.Product;

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
    public AuctionResult<TwoPartiesAuctionResult> run() {
        final var productQuantity = product.getQuantity();
        if (productQuantity == 0 || productQuantity == 1) {
            if (firstParty.getProduct().getQuantity() > secondParty.getProduct().getQuantity()) {
                return TwoPartiesAuctionResult.FIRST_BIDDER_WON;
            }
            if (secondParty.getProduct().getQuantity() > firstParty.getProduct().getQuantity()) {
                return TwoPartiesAuctionResult.SECOND_BIDDER_WON;
            }
            if (firstParty.getCash() > secondParty.getCash()) {
                return TwoPartiesAuctionResult.FIRST_BIDDER_WON;
            }
            if (secondParty.getCash() > firstParty.getCash()) {
                return TwoPartiesAuctionResult.SECOND_BIDDER_WON;
            }
            return TwoPartiesAuctionResult.TIE;
        }

        var firstPartyBid = firstParty.bid();
        var secondPartyBid = secondParty.bid();
        if (firstPartyBid > secondPartyBid) {
            final var winProduct = this.product.copy().setQuantity(2);
            return new TwoPartiesBlindBidAuction(
                    product.extract(winProduct),
                    firstParty.payCash(firstPartyBid).addProduct(winProduct),
                    secondParty.payCash(secondPartyBid))
                    .run();
        }
        if (secondPartyBid > firstPartyBid) {
            final var winProduct = this.product.copy().setQuantity(2);
            return new TwoPartiesBlindBidAuction(
                    product.extract(winProduct),
                    firstParty.payCash(firstPartyBid),
                    secondParty.payCash(secondPartyBid).addProduct(winProduct))
                    .run();
        }
        //tie
        final var winProduct = this.product.copy().setQuantity(2);
        final var tieProduct = this.product.copy().setQuantity(1);
        return new TwoPartiesBlindBidAuction(
                this.product.extract(winProduct),
                firstParty.payCash(firstPartyBid).addProduct(tieProduct),
                secondParty.payCash(secondPartyBid).addProduct(tieProduct))
                .run();
    }
}
