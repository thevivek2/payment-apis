package com.eaglesoar.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Charge {
    private String payeeAccountNumber;
    private String payerAccountNumber;
    private BigDecimal amount;
    private String type;


}
