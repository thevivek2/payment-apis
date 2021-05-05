package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.exception.DataNotFoundException;
import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountJpaRepository;
import com.eaglesoar.paymentapplication.repository.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

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
        AccountEntity accountEntity = mock(AccountEntity.class);
        when(repository.save(any(AccountEntity.class))).thenReturn(accountEntity);
        ArgumentCaptor<AccountEntity> argumentCaptor = ArgumentCaptor.forClass(AccountEntity.class);

        //when
        AccountEntity createdAccount = service.create("2", "NRS");

        //then
        verify(repository).save(argumentCaptor.capture());
        AccountEntity savedAccount = argumentCaptor.getValue();
        assertThat(savedAccount.getStatus()).isEqualTo(AccountStatus.OPEN);
        assertThat(savedAccount.getBalance()).isEqualTo(BigDecimal.ZERO);
        assertThat(savedAccount.getCurrency()).isEqualTo("NRS");
        assertThat(accountEntity).isEqualTo(createdAccount);
    }

    @Test
    void givenValidAccountNumber_whenUpdate_ShouldUpdateStatus() {
        String accountNumber = "2";
        AccountEntity accountEntity = AccountEntity.of(accountNumber, BigDecimal.ZERO, "NRS", AccountStatus.OPEN);
        when(repository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(accountEntity));
        when(repository.save(accountEntity)).thenReturn(accountEntity);
        AccountEntity update = service.update(accountNumber, AccountStatus.CLOSED);
        assertThat(update.getStatus()).isEqualTo(AccountStatus.CLOSED);

        verify(repository).findByAccountNumber(accountNumber);
        verify(repository).save(accountEntity);
    }

    @Test
    void givenInValidAccountNumber_whenUpdate_ShouldRaiseDataNotFoundException() {
        String accountNumber = "2";
        when(repository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.update(accountNumber, AccountStatus.CLOSED)).isInstanceOf(DataNotFoundException.class);
    }

}