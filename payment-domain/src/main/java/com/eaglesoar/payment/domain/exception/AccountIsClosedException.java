package com.eaglesoar.payment.domain.exception;

public class AccountIsClosedException extends BaseException {

    public AccountIsClosedException(String message) {
        super(message);
    }


    public static AccountIsClosedException of(String accountNumber) {
        return new AccountIsClosedException(String.format("Account % accountNumber not open", accountNumber));
    }

}
