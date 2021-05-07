package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountStatus;

public interface AccountService {

    AccountEntity create(String accountNumber, String currency);

    AccountEntity update(String accountNumber, AccountStatus status);
}
