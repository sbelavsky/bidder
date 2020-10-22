package com.optimax.product;

public class ProductFactory {
    public static Product createProduct(int quantity) {
        return new DefaultProduct(quantity);
    }
}
