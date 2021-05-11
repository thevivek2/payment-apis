package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.exception.AccountNotFoundException;
import com.eaglesoar.payment.domain.exception.MaxPaymentAmountExceedException;
import com.eaglesoar.payment.domain.model.AccountStatus;
import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.model.PaymentStatus;
import com.eaglesoar.payment.domain.repository.AccountRepository;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import com.eaglesoar.payment.domain.service.ChargeCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class PaymentUseCaseTest {

    @Mock
    private PaymentRepository repository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ChargeCalculator chargeCalculator;
    private final BigDecimal maxTransferLimit = BigDecimal.TEN;
    private PaymentUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new PaymentUseCase(repository, accountRepository, maxTransferLimit, chargeCalculator);
    }

    @Test
    void apply() {
        String senderAccountNumber = "2";
        String receiverAccountNumber = "3";

        Payment payment = Payment.builder().senderAccountNumber(senderAccountNumber)
                .receiverAccountNumber(receiverAccountNumber).amount(BigDecimal.ONE).build();
        when(accountRepository.isExists(senderAccountNumber)).thenReturn(true);
        when(accountRepository.isExists(receiverAccountNumber)).thenReturn(true);
        when(accountRepository.isExists(senderAccountNumber, AccountStatus.OPEN)).thenReturn(true);
        when(accountRepository.isExists(receiverAccountNumber, AccountStatus.OPEN)).thenReturn(true);

        when(repository.save(payment)).thenReturn(payment);

        Payment apply = useCase.apply(payment);
        Assertions.assertEquals(PaymentStatus.SAVED, apply.getStatus());

        verify(repository).save(payment);
        verify(accountRepository, times(2)).isExists(anyString());
        verify(accountRepository, times(2)).isExists(anyString(), eq(AccountStatus.OPEN));
        verify(chargeCalculator).calculate(payment);
    }

    @Test
    void givenPaymentAmountMoreThanLimit_whenApply_thenRaiseMaxPaymentAmountExceedException() {
        String senderAccountNumber = "2";
        String receiverAccountNumber = "3";

        Payment payment = Payment.builder().senderAccountNumber(senderAccountNumber)
                .receiverAccountNumber(receiverAccountNumber).amount(BigDecimal.valueOf(20l)).build();
        when(accountRepository.isExists(senderAccountNumber)).thenReturn(true);
        when(accountRepository.isExists(receiverAccountNumber)).thenReturn(true);
        when(accountRepository.isExists(senderAccountNumber, AccountStatus.OPEN)).thenReturn(true);
        when(accountRepository.isExists(receiverAccountNumber, AccountStatus.OPEN)).thenReturn(true);

        when(repository.save(payment)).thenReturn(payment);

        Assertions.assertThrows(MaxPaymentAmountExceedException.class, () -> useCase.apply(payment));

        verify(accountRepository, times(2)).isExists(anyString());
        verify(accountRepository, times(2)).isExists(anyString(), eq(AccountStatus.OPEN));
        verifyNoInteractions(chargeCalculator);
    }


    @Test
    void givenPaymentWithInvalidSenderAccount_whenApply_thenAccountNotFoundException() {
        String senderAccountNumber = "2";
        String receiverAccountNumber = "3";

        Payment payment = Payment.builder().senderAccountNumber(senderAccountNumber)
                .receiverAccountNumber(receiverAccountNumber).amount(BigDecimal.valueOf(20l)).build();
        when(accountRepository.isExists(senderAccountNumber)).thenReturn(false);

        when(repository.save(payment)).thenReturn(payment);

        Assertions.assertThrows(AccountNotFoundException.class, () -> useCase.apply(payment));

        verify(accountRepository).isExists(anyString());
        verifyNoInteractions(chargeCalculator);
    }
}