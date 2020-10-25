package com.optimax;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * provides messages
 */
public class Messages {
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);

    public static final String UNEXPECTED_COMPARISON = messages.getString("unexpected.comparison");
    public static final String BIDDER_UNINITIALIZED = messages.getString("bidder.uninitialized");
    public static final String BIDDER_ALREADY_INITIALIZED = messages.getString("bidder.already.initialized");
    public static final String PRODUCT_QUANTITY_BELOW_ZERO = messages.getString("product.quantity.below.zero");
    public static final String CASH_BELOW_ZERO = messages.getString("cash.below.zero");
}
