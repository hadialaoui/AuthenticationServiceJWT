package com.hadialaoui.authenticationServiceJWT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hadialaoui.authenticationServiceJWT.entities.User;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
