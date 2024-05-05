package com.example.basicauth.service;

import java.util.Optional;

import com.example.basicauth.dto.UserDto;
import com.example.basicauth.model.User;

public interface UserService {

	Optional<User> getByUsername(String username);

	UserDto createUser(UserDto request);
}
