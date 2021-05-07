package com.eaglesoar.payment.domain.exception;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(String message) {
        super(message);
    }


    public static AccountNotFoundException of(String accountNumber) {
        return new AccountNotFoundException(String.format("Account %s accountNumber not exists", accountNumber));
    }

}
