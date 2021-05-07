package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.repository.UserEntity;
import com.eaglesoar.paymentapplication.v1.resource.UserRequest;
import com.eaglesoar.paymentapplication.v1.resource.UserResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRequest user);

    UserResource toResource(UserEntity entity);
}
