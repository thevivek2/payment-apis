package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.exception.AccountIsClosedException;
import com.eaglesoar.payment.domain.exception.AccountNotFoundException;
import com.eaglesoar.payment.domain.exception.MaxPaymentAmountExceedException;
import com.eaglesoar.payment.domain.model.AccountStatus;
import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.repository.AccountRepository;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import com.eaglesoar.payment.domain.service.ChargeCalculator;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static com.eaglesoar.payment.domain.model.PaymentStatus.SAVED;

@AllArgsConstructor
public class PaymentUseCase {

    private final PaymentRepository repository;
    private final AccountRepository accountRepository;
    private final BigDecimal maxTransferLimit;
    private final ChargeCalculator chargeCalculator;

    public Payment apply(Payment payment) {
        validateAccountExists(payment.getSenderAccountNumber());
        validateAccountExists(payment.getReceiverAccountNumber());
        validateAccountStatus(payment.getSenderAccountNumber());
        validateAccountStatus(payment.getReceiverAccountNumber());
        validateMaxTransferLimit(payment);
        chargeCalculator.calculate(payment);
        payment.setStatus(SAVED);
        return repository.save(payment);
    }

    private void validateMaxTransferLimit(Payment payment) {
        if (maxTransferLimit.compareTo(payment.getAmount()) < 0)
            throw MaxPaymentAmountExceedException.of(maxTransferLimit);
    }

    private void validateAccountStatus(String accountNumber) {
        if (!accountRepository.isExists(accountNumber, AccountStatus.OPEN))
            throw AccountIsClosedException.of(accountNumber);
    }

    private void validateAccountExists(String accountNumber) {
        if (!accountRepository.isExists(accountNumber))
            throw AccountNotFoundException.of(accountNumber);
    }

}
