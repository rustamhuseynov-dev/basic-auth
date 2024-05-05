package com.example.basicauth;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.basicauth.dto.UserDto;
import com.example.basicauth.model.Role;
import com.example.basicauth.service.UserService;

@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner{
	
	private final UserService userService;

	public BasicAuthApplication(UserService userService) {
		super();
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createData();
		
	}
	
	private void createData() {
		UserDto userDto = UserDto.builder()
				.name("Rustam")
				.username("rustamh")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
         userService.createUser(userDto);
         
         UserDto userDto1 = UserDto.builder()
 				.name("Kamil")
 				.username("kamilm")
 				.password("pass")
 				.authorities(Set.of(Role.ROLE_MOD))
 				.build();
          userService.createUser(userDto1);
          
          UserDto userDto2 = UserDto.builder()
  				.name("Saleh")
  				.username("saleha")
  				.password("pass")
  				.authorities(Set.of(Role.ROLE_USER))
  				.build();
           userService.createUser(userDto2);
	}

}
