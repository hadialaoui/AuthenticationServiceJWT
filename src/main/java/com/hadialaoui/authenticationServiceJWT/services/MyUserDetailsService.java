package com.hadialaoui.authenticationServiceJWT.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hadialaoui.authenticationServiceJWT.entities.User;
import com.hadialaoui.authenticationServiceJWT.models.MyUserDetails;
import com.hadialaoui.authenticationServiceJWT.repositories.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(username);
		 if(!user.isPresent())
			 throw  new UsernameNotFoundException("Not found : "+username);
		//user.orElseThrow(() -> new UsernameNotFoundException("Not found : "+username));
		//return new MyUserDetails(username);
		return user.map(MyUserDetails::new).get();
	}

}
