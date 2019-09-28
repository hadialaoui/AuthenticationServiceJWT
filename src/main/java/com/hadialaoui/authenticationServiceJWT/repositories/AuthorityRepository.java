package com.hadialaoui.authenticationServiceJWT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hadialaoui.authenticationServiceJWT.entities.Authority;


@EnableJpaRepositories
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	Optional<Authority> findByAuthority(String authorityName);
}
