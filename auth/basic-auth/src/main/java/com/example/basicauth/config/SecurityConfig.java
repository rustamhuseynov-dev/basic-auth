package com.example.basicauth.config;

import org.aspectj.asm.internal.HandleProviderDelimiter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.basicauth.model.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security,HandlerMappingIntrospector introspector) throws Exception {
		
		MvcRequestMatcher.Builder mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector); 
		
		security
		        .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				.csrf(csrfConfig -> 
						csrfConfig.ignoringRequestMatchers(mvcRequestMatcher.pattern("/public/**"))
						           .ignoringRequestMatchers(PathRequest.toH2Console()))
				.authorizeHttpRequests(x -> 
				x.requestMatchers(mvcRequestMatcher.pattern("/public/**")).permitAll()
				.requestMatchers(mvcRequestMatcher.pattern("/private/admin/**")).hasRole(Role.ROLE_ADMIN.getValue())
				.requestMatchers(mvcRequestMatcher.pattern("/private/**")).hasAnyRole(
						Role.ROLE_ADMIN.getValue(),
						Role.ROLE_MOD.getValue(),
						Role.ROLE_USER.getValue()
						)
				.requestMatchers(PathRequest.toH2Console()).hasRole(Role.ROLE_ADMIN.getValue())
				.anyRequest().authenticated()
						)
				
				.formLogin(AbstractHttpConfigurer::disable)
				.httpBasic(Customizer.withDefaults());

		return security.build();
	}
}
