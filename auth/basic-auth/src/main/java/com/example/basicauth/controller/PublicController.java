package com.example.basicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

	@GetMapping
	public String helloWorld() {
		return "Hello World from public endpoint";
	}
}
