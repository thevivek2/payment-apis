package com.eaglesoar.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class Payment {

    private Long id;
    private String debtorAccountNumber;
    private String creditorAccountNumber;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;

}
