package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.exception.AccountIsClosedException;
import com.eaglesoar.payment.domain.exception.AccountNotFoundException;
import com.eaglesoar.payment.domain.exception.MaxPaymentAmountExceedException;
import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.repository.AccountRepository;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static com.eaglesoar.payment.domain.model.AccountStatus.OPEN;
import static com.eaglesoar.payment.domain.model.PaymentStatus.SAVED;

@AllArgsConstructor
public class PaymentUseCase {

    private final PaymentRepository repository;
    private final AccountRepository accountRepository;
    private final BigDecimal maxTransferLimit;

    public Payment apply(Payment payment) {
        validateSenderAndReceiverAccountExists(payment);
        validateSenderAndReceiverAccountStatus(payment);
        validateMaxTransferLimit(payment);
        payment.setStatus(SAVED);
        return repository.save(payment);
    }

    private void validateMaxTransferLimit(Payment payment) {
        if (maxTransferLimit.compareTo(payment.getAmount()) < 0)
            throw MaxPaymentAmountExceedException.of(maxTransferLimit);
    }

    private void validateSenderAndReceiverAccountStatus(Payment payment) {
        if (!accountRepository.isExists(payment.getSenderAccountNumber(), OPEN))
            throw AccountIsClosedException.of(payment.getSenderAccountNumber());
        if (!accountRepository.isExists(payment.getReceiverAccountNumber(), OPEN))
            throw AccountIsClosedException.of(payment.getSenderAccountNumber());
    }

    private void validateSenderAndReceiverAccountExists(Payment payment) {
        if (!accountRepository.isExists(payment.getSenderAccountNumber()))
            throw AccountNotFoundException.of(payment.getSenderAccountNumber());
        if (!accountRepository.isExists(payment.getReceiverAccountNumber()))
            throw AccountNotFoundException.of(payment.getReceiverAccountNumber());
    }
}
