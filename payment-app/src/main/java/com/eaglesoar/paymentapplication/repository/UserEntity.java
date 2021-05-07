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
    @Column(unique = true)
    private String mobileNumber;
    @Column(unique = true)
    private String email;
    private String uuid;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
}
