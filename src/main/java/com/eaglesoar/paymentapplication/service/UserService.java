package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.UserEntity;
import com.eaglesoar.paymentapplication.repository.UserJpaRepository;
import com.eaglesoar.paymentapplication.repository.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserJpaRepository repository;

    @Autowired
    public UserService(UserJpaRepository repository) {
        this.repository = repository;
    }

    public UserEntity create(UserEntity user) {
        user.setUuid(UUID.randomUUID().toString());
        user.setStatus(UserStatus.CREATED);
        return repository.save(user);
    }
}
