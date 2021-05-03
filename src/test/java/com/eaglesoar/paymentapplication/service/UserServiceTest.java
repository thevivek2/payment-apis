package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.UserEntity;
import com.eaglesoar.paymentapplication.repository.UserJpaRepository;
import com.eaglesoar.paymentapplication.repository.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserJpaRepository repository;
    @Mock
    private AccountService accountService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(repository, accountService);
    }

    @Test
    void givenValidUser_whenCreate_thenStatusIsCreated() {
        //given
        UserEntity user = user();
        when(repository.save(user)).thenReturn(user);

        //when
        UserEntity createdUser = userService.create(user);

        //then
        assertThat(createdUser.getStatus()).isEqualTo(UserStatus.CREATED);
        assertThat(createdUser.getUuid()).isNotEmpty();

        verify(accountService).create(user.getMobileNumber(), "NRS");
        verify(repository).save(user);
    }


    private static UserEntity user() {
        UserEntity user = new UserEntity();
        user.setEmail("thevivek2@gmail.com");
        user.setMobileNumber("2222");
        user.setName("Vivek Adhikari");
        return user;
    }
}