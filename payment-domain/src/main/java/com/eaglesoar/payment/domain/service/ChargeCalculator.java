package com.eaglesoar.payment.domain.service;

import com.eaglesoar.payment.domain.model.Charge;
import com.eaglesoar.payment.domain.model.Payment;

import java.util.List;

public interface ChargeCalculator {

    List<Charge> calculate(Payment payment);
}
