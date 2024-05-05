package com.example.basicauth.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.basicauth.dto.UserDto;
import com.example.basicauth.model.User;
import com.example.basicauth.repository.UserRepository;
import com.example.basicauth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	private final BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public UserDto createUser(UserDto request) {
		User newUser = User.builder().name(request.name()).username(request.username())
				.password(passwordEncoder.encode(request.password())).authorities(request.authorities())
				.accountNonExpired(true).accountNonLocked(true).isEnabled(true).credentialsNonExpired(true).build();
		repository.save(newUser);
		// response
		UserDto userDto = new UserDto(newUser.getName(), newUser.getUsername(), null, newUser.getAuthorities());
		return userDto;
	}

}
