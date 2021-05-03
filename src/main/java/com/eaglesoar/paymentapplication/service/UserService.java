package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.exception.DuplicateDataException;
import com.eaglesoar.paymentapplication.repository.UserEntity;
import com.eaglesoar.paymentapplication.repository.UserJpaRepository;
import com.eaglesoar.paymentapplication.repository.UserStatus;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private static final String CURRENCY = "NRS";

    private final UserJpaRepository repository;
    private final AccountService accountService;

    public UserEntity create(UserEntity user) {
        enrich(user);
        accountService.create(user.getMobileNumber(), CURRENCY);
        try {
            return repository.save(user);
        } catch (ConstraintViolationException e) {
            throw new DuplicateDataException(String.format("Mobile number %s is already used", user.getMobileNumber()));
        }
    }

    private void enrich(UserEntity user) {
        user.setUuid(UUID.randomUUID().toString());
        user.setStatus(UserStatus.CREATED);
    }
}
