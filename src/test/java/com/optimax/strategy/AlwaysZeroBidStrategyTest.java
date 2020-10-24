package com.optimax.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlwaysZeroBidStrategyTest {

    private final AlwaysZeroBidStrategy test = new AlwaysZeroBidStrategy();

    @Test
    void bid() {
        assertEquals(0, test.bid());
    }
}