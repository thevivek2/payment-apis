package com.eaglesoar.payment.domain.exception;

import java.math.BigDecimal;

public class MaxPaymentAmountExceedException extends BaseException {

    public MaxPaymentAmountExceedException(String message) {
        super(message);
    }


    public static MaxPaymentAmountExceedException of(BigDecimal amount) {
        return new MaxPaymentAmountExceedException(String.format("Payment exceeds max payment amount %s ", amount));
    }

}
