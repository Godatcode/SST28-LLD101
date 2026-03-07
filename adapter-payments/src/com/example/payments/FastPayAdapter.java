package com.example.payments;

import java.util.Objects;

/**
 * Adapts FastPayClient to the PaymentGateway interface.
 * FastPayClient uses payNow(custId, amountCents) — we just delegate to it.
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        return client.payNow(customerId, amountCents);
    }
}
