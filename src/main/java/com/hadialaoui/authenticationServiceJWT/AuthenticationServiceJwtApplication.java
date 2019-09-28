package com.hadialaoui.authenticationServiceJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages="com.hadialaoui.authenticationServiceJWT.*")
@SpringBootApplication
public class AuthenticationServiceJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceJwtApplication.class, args);
	}

}
