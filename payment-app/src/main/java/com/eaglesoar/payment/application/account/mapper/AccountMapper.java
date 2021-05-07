package com.eaglesoar.payment.application.account.mapper;

import com.eaglesoar.payment.application.account.repository.AccountEntity;
import com.eaglesoar.payment.application.v1.resource.AccountRequest;
import com.eaglesoar.payment.application.v1.resource.AccountResource;
import com.eaglesoar.payment.application.v1.resource.AccountUpdateRequest;
import com.eaglesoar.payment.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResource toResource(AccountEntity entity);

    AccountResource toResource(Account model);

    List<AccountResource> toResource(List<AccountEntity> entity);

    Account toModel(AccountUpdateRequest request);

    @Mapping(source = "mobileNumber", target = "accountNumber")
    Account toModel(AccountRequest accountRequest);

    AccountEntity toEntity(Account account);

    Account toModel(AccountEntity entity);
}
