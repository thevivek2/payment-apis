package com.eaglesoar.paymentapplication.payment.repository;

import com.eaglesoar.payment.model.Payment;
import com.eaglesoar.payment.repository.PaymentRepository;
import com.eaglesoar.paymentapplication.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository repository;
    private final PaymentMapper mapper;

    public PaymentRepositoryImpl(PaymentJpaRepository repository, PaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Payment save(Payment payment) {
        return mapper.toModel(repository.save(mapper.toEntity(payment)));
    }
}
