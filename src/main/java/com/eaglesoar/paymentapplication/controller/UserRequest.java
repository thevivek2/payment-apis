package com.eaglesoar.paymentapplication.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String email;

}
