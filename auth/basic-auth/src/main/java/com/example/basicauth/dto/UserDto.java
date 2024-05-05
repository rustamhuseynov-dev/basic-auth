package com.example.basicauth.dto;

import java.util.Set;

import com.example.basicauth.model.Role;

import lombok.Builder;

@Builder
public record UserDto(
		String name,
        String username,
        String password,
        Set<Role> authorities
) {

}
