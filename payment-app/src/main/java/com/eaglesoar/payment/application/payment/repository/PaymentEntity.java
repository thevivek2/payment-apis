package com.eaglesoar.payment.application.payment.repository;

import com.eaglesoar.payment.domain.model.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String debtorAccountNumber;
    private String creditorAccountNumber;
    private BigDecimal amount;
    private String currency;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

}
