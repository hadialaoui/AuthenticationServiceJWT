package com.hadialaoui.authenticationServiceJWT.services;

import com.hadialaoui.authenticationServiceJWT.entities.Authority;
import com.hadialaoui.authenticationServiceJWT.entities.User;

public interface UserAuthoritesService {
    User getUserByUsername(String username);
    User saveUser(User user);
    Authority saveAuthority(Authority authority);
    void addAuthorityToUser(String username,String authority);
}
