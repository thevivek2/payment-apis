package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.repository.UserStatus;
import lombok.Data;

@Data
public class UserResource {

    private String uuid;
    private UserStatus status;

}
