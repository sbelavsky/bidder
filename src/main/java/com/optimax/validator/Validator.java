package com.optimax.validator;

public interface Validator<T> {
    void validate(T target);
}
