package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.model.PaymentStatus;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PaymentUseCaseTest {

    @Mock
    private PaymentRepository repository;
    private PaymentUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new PaymentUseCase(repository);
    }

    @Test
    void apply() {
        Payment payment = Payment.builder().build();
        Mockito.when(repository.save(payment)).thenReturn(payment);

        Payment apply = useCase.apply(payment);
        Assertions.assertEquals(PaymentStatus.SAVED, apply.getStatus());

        Mockito.verify(repository).save(payment);
    }
}