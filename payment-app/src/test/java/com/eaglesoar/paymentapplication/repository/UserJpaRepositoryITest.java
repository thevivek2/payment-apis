package com.eaglesoar.paymentapplication.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserJpaRepositoryITest {

    @Autowired
    private UserJpaRepository repository;

    @Test
    void save() {
        UserEntity user = new UserEntity();
        user.setEmail("thevivek2@gmail.com");
        user.setMobileNumber("222");
        user.setStatus(UserStatus.CREATED);
        UserEntity save = repository.save(user);
        assertThat(save.getId()).isNotNull();
    }
}