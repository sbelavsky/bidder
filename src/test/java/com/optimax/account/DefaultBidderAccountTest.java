package com.optimax.account;

import com.optimax.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultBidderAccountTest {
    private final Product product = mock(Product.class);
    private final Product addProduct = mock(Product.class);
    private final Product resultProduct = mock(Product.class);
    private final int cash = 100;
    private final DefaultBidderAccount test = new DefaultBidderAccount(product, cash);

    @BeforeEach
    public void setUp() {
        when(product.getQuantity()).thenReturn(10);
        when(product.copy()).thenReturn(product);
        when(addProduct.getQuantity()).thenReturn(10);
        when(resultProduct.getQuantity()).thenReturn(30);
        when(product.add(addProduct)).thenReturn(resultProduct);
    }

    @Test
    void getProduct() {
        assertEquals(product, test.getProduct());
    }

    @Test
    void getCash() {
        assertEquals(cash, test.getCash());
    }

    @Test
    void addProduct() {
        final var updated = test.addProduct(addProduct);
        assertEquals(resultProduct, updated.getProduct());
    }

    @Test
    void payCash() {
        var updated = test.payCash(50);
        assertEquals(cash - 50, updated.getCash());
    }

    @Test
    void testEquals() {
        var account = new DefaultBidderAccount(product, cash);
        assertEquals(account, test);
    }

    @Test
    void testHashCode() {
        var account = new DefaultBidderAccount(product, cash);
        assertEquals(account.hashCode(), test.hashCode());
    }

    @Test
    void copy() {
        var copy = test.copy();
        assertEquals(test, copy);
    }
}