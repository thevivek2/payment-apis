package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.exception.DataNotFoundException;
import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountJpaRepository;
import com.eaglesoar.paymentapplication.repository.AccountStatus;
import com.eaglesoar.paymentapplication.service.AccountService;
import com.eaglesoar.paymentapplication.v1.api.AccountApi;
import com.eaglesoar.paymentapplication.v1.resource.AccountResource;
import com.eaglesoar.paymentapplication.v1.resource.AccountUpdateRequest;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Api(tags = "Account")
public class AccountController implements AccountApi {

    private final AccountJpaRepository repository;
    private final AccountMapper mapper;
    private final AccountService accountService;

    @Override
    public ResponseEntity<AccountResource> get(String accountNumber) {
        return ResponseEntity.ok(repository.findByAccountNumber(accountNumber).map(mapper::toResource)
                .orElseThrow(() -> new DataNotFoundException(String.format("AccountNumber %s not exists", accountNumber))));
    }

    @Override
    public ResponseEntity<AccountResource> patch(String accountNumber, AccountUpdateRequest accountUpdateRequest) {
        return ResponseEntity.ok(mapper.toResource(accountService.update(accountNumber,
                AccountStatus.valueOf(accountUpdateRequest.getStatus().name()))));
    }

    @Override
    public ResponseEntity<List<AccountResource>> getHistory(String accountNumber) {
        List<AccountResource> accountResources = repository.findByAccountNumber(accountNumber).map((account) -> {
            List<AccountEntity> collect = repository.findRevisions(account.getId()).stream().map(Revision::getEntity).collect(Collectors.toList());
            return mapper.toResource(collect);
        }).orElseThrow(() -> new DataNotFoundException(String.format("AccountNumber %s not exists", accountNumber)));
        return ResponseEntity.ok(accountResources);
    }
}
