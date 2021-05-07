package com.eaglesoar.payment.application.payment.controller;

import com.eaglesoar.payment.application.v1.api.PaymentApi;
import com.eaglesoar.payment.application.v1.resource.PaymentRequest;
import com.eaglesoar.payment.application.v1.resource.PaymentResponse;
import com.eaglesoar.payment.domain.usecase.PaymentUseCase;
import com.eaglesoar.payment.application.payment.mapper.PaymentMapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Payment")
@AllArgsConstructor
public class PaymentController implements PaymentApi {

    private final PaymentUseCase useCase;
    private final PaymentMapper mapper;

    @Override
    public ResponseEntity<PaymentResponse> createPayment(PaymentRequest paymentRequest) {
        return ResponseEntity.ok().body(mapper.toResource(useCase.apply(mapper.toModel(paymentRequest))));
    }
}
