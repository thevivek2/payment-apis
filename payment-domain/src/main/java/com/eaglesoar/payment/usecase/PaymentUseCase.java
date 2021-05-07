package com.eaglesoar.payment.usecase;

import com.eaglesoar.payment.model.Payment;
import com.eaglesoar.payment.model.PaymentStatus;
import com.eaglesoar.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentUseCase {

    private final PaymentRepository repository;

    public Payment apply(Payment payment) {
        payment.setStatus(PaymentStatus.SAVED);
        return repository.save(payment);
    }
}
