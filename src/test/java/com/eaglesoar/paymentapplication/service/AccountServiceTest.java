package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountJpaRepository;
import com.eaglesoar.paymentapplication.repository.AccountStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest {

    @Mock
    private AccountJpaRepository repository;

    private AccountService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AccountServiceImpl(repository);
    }

    @Test
    void create() {

        //given
        AccountEntity accountEntity = Mockito.mock(AccountEntity.class);
        Mockito.when(repository.save(Mockito.any(AccountEntity.class))).thenReturn(accountEntity);
        ArgumentCaptor<AccountEntity> argumentCaptor = ArgumentCaptor.forClass(AccountEntity.class);

        //when
        AccountEntity createdAccount = service.create("2", "NRS");

        //then
        Mockito.verify(repository).save(argumentCaptor.capture());
        AccountEntity savedAccount = argumentCaptor.getValue();
        assertThat(savedAccount.getStatus()).isEqualTo(AccountStatus.OPEN);
        assertThat(savedAccount.getBalance()).isEqualTo(BigDecimal.ZERO);
        assertThat(savedAccount.getCurrency()).isEqualTo("NRS");
        Assertions.assertThat(accountEntity).isEqualTo(createdAccount);
    }
}