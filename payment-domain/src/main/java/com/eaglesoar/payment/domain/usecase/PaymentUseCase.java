package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.model.PaymentStatus;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentUseCase {

    private final PaymentRepository repository;

    public Payment apply(Payment payment) {
        payment.setStatus(PaymentStatus.SAVED);
        return repository.save(payment);
    }
}
