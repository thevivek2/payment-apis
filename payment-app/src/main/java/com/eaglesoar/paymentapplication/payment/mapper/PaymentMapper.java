package com.eaglesoar.paymentapplication.payment.mapper;

import com.eaglesoar.payment.model.Payment;
import com.eaglesoar.paymentapplication.payment.repository.PaymentEntity;
import com.eaglesoar.paymentapplication.v1.resource.PaymentRequest;
import com.eaglesoar.paymentapplication.v1.resource.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toModel(PaymentEntity entity);

    PaymentResponse toResource(Payment payment);

    Payment toModel(PaymentRequest paymentRequest);
}
