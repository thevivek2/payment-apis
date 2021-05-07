package com.eaglesoar.payment.domain.repository;

import com.eaglesoar.payment.domain.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);

}
