package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResource> create(@Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(mapper.toResource(service.create(mapper.toEntity(user))));
    }


}
