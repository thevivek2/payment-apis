package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.repository.UserEntity;
import com.eaglesoar.paymentapplication.repository.UserJpaRepository;
import com.eaglesoar.paymentapplication.repository.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Mock
    private UserJpaRepository repository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(repository);
    }

    @Test
    void givenValidUser_whenCreate_thenStatusIsCreated() {
        //given
        UserEntity user = user();
        Mockito.when(repository.save(user)).thenReturn(user);

        //when
        UserEntity createdUser = userService.create(user);

        //then
        assertThat(createdUser.getStatus()).isEqualTo(UserStatus.CREATED);
        assertThat(createdUser.getUuid()).isNotEmpty();
    }


    private static UserEntity user() {
        UserEntity user = new UserEntity();
        user.setEmail("thevivek2@gmail.com");
        user.setMobileNumber("2222");
        user.setName("Vivek Adhikari");
        return user;
    }
}