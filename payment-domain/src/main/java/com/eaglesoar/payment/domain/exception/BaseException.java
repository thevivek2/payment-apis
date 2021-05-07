package com.eaglesoar.payment.domain.exception;

public abstract class BaseException extends RuntimeException {

    public BaseException(String message){
        super(message);
    }
}
