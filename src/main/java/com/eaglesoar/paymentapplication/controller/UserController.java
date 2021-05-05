package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.service.UserService;
import com.eaglesoar.paymentapplication.v1.api.UserApi;
import com.eaglesoar.paymentapplication.v1.resource.UserRequest;
import com.eaglesoar.paymentapplication.v1.resource.UserResource;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags = {"User"})
public class UserController implements UserApi {

    private final UserService service;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<UserResource> create(UserRequest userRequest) {
        return ResponseEntity.ok(mapper.toResource(service.create(mapper.toEntity(userRequest))));
    }
}
