package com.hadialaoui.authenticationServiceJWT.dataSeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hadialaoui.authenticationServiceJWT.entities.Authority;
import com.hadialaoui.authenticationServiceJWT.entities.User;
import com.hadialaoui.authenticationServiceJWT.services.UserAuthoritesService;

@Component
public class UsersAthoritiesCommandLineRunner implements CommandLineRunner{

	@Autowired
	UserAuthoritesService  userAuthoritesService;
	@Override
	public void run(String... args) throws Exception {
	/*	userAuthoritesService.saveUser(new User("admin", "admin", true));
		userAuthoritesService.saveUser(new User("userhadi", "userhadi", true));
		
		userAuthoritesService.saveAuthority(new Authority("ROLE_ADMIN"));
		userAuthoritesService.saveAuthority(new Authority("ROLE_USER"));
		
		userAuthoritesService.addAuthorityToUser("admin", "ROLE_ADMIN");
		userAuthoritesService.addAuthorityToUser("userhadi", "ROLE_USER");*/
		
		
	}

}
