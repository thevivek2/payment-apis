package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.v1.resource.AccountResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResource toResource(AccountEntity entity);

    List<AccountResource> toResource(List<AccountEntity> entity);
}
