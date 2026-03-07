package com.example.payments;

import java.util.Objects;

/**
 * Adapts SafeCashClient to the PaymentGateway interface.
 * SafeCashClient has a two-step flow: createPayment() then confirm() —
 * the adapter hides that detail from OrderService.
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
