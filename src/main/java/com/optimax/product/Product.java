package com.optimax.product;

import com.optimax.Copyable;

/**
 * Represents auction's product
 */
public interface Product extends Copyable<Product> {
    int getQuantity();

    Product setQuantity(int amount);

    Product add(Product product);

    Product extract(Product product);

    boolean isEmpty();
}
