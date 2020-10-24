package com.optimax.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultProductTest {

    @Test
    void getQuantity() {
        var test = new DefaultProduct(10);
        assertEquals(10, test.getQuantity());
    }

    @Test
    void setQuantity() {
        var product = new DefaultProduct(0);
        final var test = product.setQuantity(20);
        assertEquals(20, test.getQuantity());
    }

    @Test
    void setNegativeQuantity() {
        var product = new DefaultProduct(0);
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-20));
    }

    @Test
    void add() {
        var product = new DefaultProduct(10);
        var productToAdd = new DefaultProduct(30);
        var test = product.add(productToAdd);
        assertEquals(40, test.getQuantity());
    }

    @Test
    void creatingWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new DefaultProduct(-10));
    }

    @Test
    void extract() {
        var product = new DefaultProduct(30);
        var productToExtract = new DefaultProduct(10);
        var test = product.extract(productToExtract);
        assertEquals(20, test.getQuantity());
    }

    @Test
    void extractMoreThanProductHas() {
        var product = new DefaultProduct(30);
        var productToExtract = new DefaultProduct(40);
        assertThrows(IllegalArgumentException.class, () -> product.extract(productToExtract));
    }

    @Test
    void isEmpty() {
        var test = new DefaultProduct(0);
        assertTrue(test.isEmpty());
    }

    @Test
    void isEmptyFalse() {
        var test = new DefaultProduct(10);
        assertFalse(test.isEmpty());
    }

    @Test
    void copy() {
        var product = new DefaultProduct(40);
        var test = product.copy();
        assertEquals(40, test.getQuantity());
    }

    @Test
    void equals() {
        var test1 = new DefaultProduct(10);
        var test2 = new DefaultProduct(10);
        assertEquals(test1, test2);
    }

    @Test
    void notEquals() {
        var test1 = new DefaultProduct(10);
        var test2 = new DefaultProduct(20);
        assertNotEquals(test1, test2);
    }

    @Test
    void hashcode() {
        var test1 = new DefaultProduct(10);
        var test2 = new DefaultProduct(10);
        assertEquals(test1.hashCode(), test2.hashCode());
    }
}