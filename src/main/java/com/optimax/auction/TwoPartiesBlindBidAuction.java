package com.optimax.auction;

import com.optimax.bidder.Bidder;
import com.optimax.product.DefaultProduct;
import com.optimax.product.Product;

public class TwoPartiesBlindBidAuction implements Auction<TwoPartiesAuctionResult> {

    private final Bidder firstBidder;
    private final Bidder secondBidder;
    private final Product product;

    public TwoPartiesBlindBidAuction(Product product, Bidder firstBidder, Bidder secondBidder) {
        this.firstBidder = firstBidder;
        this.secondBidder = secondBidder;
        this.product = product;
    }

    @Override
    public AuctionResult<TwoPartiesAuctionResult> run() {
        final var productQuantity = product.getQuantity();
        if (productQuantity == 0 || productQuantity == 1) {
            //todo find the winner
            return null;
        }
        var firstBidderBid = firstBidder.placeBid();
        var secondBidderBid = secondBidder.placeBid();
        if (firstBidderBid > secondBidderBid) {
            firstBidder.init(first);
            return new TwoPartiesBlindBidAuction(
                    new DefaultProduct(productQuantity - 2),
                    firstBidder,
                    secondBidder).run();
        }
    }
}
