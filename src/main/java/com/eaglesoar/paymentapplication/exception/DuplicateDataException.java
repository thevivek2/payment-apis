package com.eaglesoar.paymentapplication.exception;

public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException(String message) {
        super(message);
    }

    public static DuplicateDataException of(String message) {
        return new DuplicateDataException(message);
    }
}
