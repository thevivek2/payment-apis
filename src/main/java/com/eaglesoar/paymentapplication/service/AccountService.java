package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.AccountEntity;

public interface AccountService {

    AccountEntity create(String accountNumber, String currency);
}
