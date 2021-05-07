package com.eaglesoar.payment.application.account.controller;

import com.eaglesoar.payment.application.v1.resource.AccountUpdateRequest;
import com.eaglesoar.payment.domain.usecase.AccountUseCase;
import com.eaglesoar.payment.domain.exception.AccountNotFoundException;
import com.eaglesoar.payment.application.account.mapper.AccountMapper;
import com.eaglesoar.payment.application.account.repository.AccountEntity;
import com.eaglesoar.payment.application.account.repository.AccountJpaRepository;
import com.eaglesoar.payment.application.v1.api.AccountApi;
import com.eaglesoar.payment.application.v1.resource.AccountRequest;
import com.eaglesoar.payment.application.v1.resource.AccountResource;
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
    private final AccountUseCase useCase;

    @Override
    public ResponseEntity<AccountResource> createAccount(AccountRequest accountRequest) {
        return ResponseEntity.ok(mapper.toResource(useCase.create(mapper.toModel(accountRequest))));
    }

    @Override
    public ResponseEntity<AccountResource> getAccount(String accountNumber) {
        return ResponseEntity.ok(repository.findByAccountNumber(accountNumber).map(mapper::toResource)
                .orElseThrow(() -> AccountNotFoundException.of(accountNumber)));
    }

    @Override
    public ResponseEntity<AccountResource> patchAccount(String accountNumber, AccountUpdateRequest accountUpdateRequest) {
        return ResponseEntity.ok(mapper.toResource(useCase.update(accountNumber, mapper.toModel(accountUpdateRequest))));
    }

    @Override
    public ResponseEntity<List<AccountResource>> getAccountHistory(String accountNumber) {
        List<AccountResource> accountResources = repository.findByAccountNumber(accountNumber).map((account) -> {
            List<AccountEntity> collect = repository.findRevisions(account.getId()).stream().map(Revision::getEntity).collect(Collectors.toList());
            return mapper.toResource(collect);
        }).orElseThrow(() -> AccountNotFoundException.of(accountNumber));
        return ResponseEntity.ok(accountResources);
    }

}
