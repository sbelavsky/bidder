package com.optimax.product;

import com.optimax.Messages;

import java.util.Objects;

public class DefaultProduct implements Product {
    private final int quantity;

    public DefaultProduct(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public DefaultProduct(DefaultProduct product) {
        this(product.getQuantity());
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public Product setQuantity(int amount) {
        return new DefaultProduct(amount);
    }

    @Override
    public Product add(Product product) {
        return new DefaultProduct(this.quantity + product.getQuantity());
    }

    @Override
    public Product extract(Product product) {
        return new DefaultProduct(quantity - product.getQuantity());
    }

    @Override
    public boolean isEmpty() {
        return quantity == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultProduct that = (DefaultProduct) o;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public Product copy() {
        return new DefaultProduct(this);
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(Messages.PRODUCT_QUANTITY_BELOW_ZERO);
        }
    }
}
