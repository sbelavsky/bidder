package com.optimax.bidder;

import com.optimax.account.BidderAccount;
import com.optimax.product.Product;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseBidderTest {
    protected final String name = "test";
    protected final Product product = mock(Product.class);
    protected final Product updatedProduct = mock(Product.class);
    protected final int payAmount = 50;
    protected final BidderAccount bidderAccount = mock(BidderAccount.class);
    protected final BidderAccount updatedCashBidderAccount = mock(BidderAccount.class);
    protected final BidderAccount updatedProductBidderAccount = mock(BidderAccount.class);

    @BeforeEach
    public void setUpBase() {
        when(product.getQuantity()).thenReturn(10);
        when(bidderAccount.getProduct()).thenReturn(product);
        when(bidderAccount.copy()).thenReturn(bidderAccount);
        when(updatedCashBidderAccount.getProduct()).thenReturn(product);
        when(bidderAccount.payCash(anyInt())).thenReturn(updatedCashBidderAccount);
        when(updatedProductBidderAccount.getProduct()).thenReturn(updatedProduct);
        when(bidderAccount.addProduct(any(Product.class))).thenReturn(updatedProductBidderAccount);
    }
}
