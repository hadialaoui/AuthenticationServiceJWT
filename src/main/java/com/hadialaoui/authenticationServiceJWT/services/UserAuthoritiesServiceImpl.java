package com.hadialaoui.authenticationServiceJWT.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hadialaoui.authenticationServiceJWT.entities.Authority;
import com.hadialaoui.authenticationServiceJWT.entities.User;
import com.hadialaoui.authenticationServiceJWT.repositories.AuthorityRepository;
import com.hadialaoui.authenticationServiceJWT.repositories.UserRepository;

@Service
public class UserAuthoritiesServiceImpl implements UserAuthoritesService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public User getUserByUsername(String username) {
	   Optional<User> user = userRepository.findByUsername(username);
	   
	   if(!user.isPresent())
		   throw new UsernameNotFoundException("User Not Found : "+username);
			   
		return user.get();
	}

	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public Authority saveAuthority(Authority authority) {

		return authorityRepository.save(authority);
	}

	@Override
	public void addAuthorityToUser(String username, String authorityName) {
		User user = getUserByUsername(username);
		Optional<Authority> authority = authorityRepository.findByAuthority(authorityName); 
		if(!authority.isPresent()) {
			throw new RuntimeException();
		}
		user.getRoles().add(authority.get());
		saveUser(user);
	}

}
