package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.repository.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRequest user);

    UserResource toResource(UserEntity entity);
}
