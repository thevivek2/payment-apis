package com.eaglesoar.payment.application.payment.mapper;

import com.eaglesoar.payment.application.payment.repository.PaymentEntity;
import com.eaglesoar.payment.application.v1.resource.PaymentRequest;
import com.eaglesoar.payment.application.v1.resource.PaymentResponse;
import com.eaglesoar.payment.domain.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toModel(PaymentEntity entity);

    PaymentResponse toResource(Payment payment);

    Payment toModel(PaymentRequest paymentRequest);
}
