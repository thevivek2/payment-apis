package com.eaglesoar.paymentapplication.repository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobileNumber;
    private String email;
    private String uuid;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
}
