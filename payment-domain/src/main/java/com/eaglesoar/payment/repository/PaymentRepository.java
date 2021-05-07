package com.eaglesoar.payment.repository;

import com.eaglesoar.payment.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
