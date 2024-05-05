package com.example.basicauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/private")
public class PrivateController {

	@GetMapping
	public String helloWorldPrivate() {
		return "Hello World from Private endPoint";
	}

	@PreAuthorize(value = "hasRole('USER')")
	@GetMapping(path = "/user")
	public String helloWorldPrivateUser() {
		return "Hello World from user private endPoint";
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@GetMapping(path = "/admin")
	public String helloWorldPrivateAdmin() {
		return "Hello World from admin private endPoint";
	}
}
