package com.optimax;

/**
 * instance can be copied
 *
 * @param <T> is a type of the instance
 */
public interface Copyable<T> {
    T copy();
}
