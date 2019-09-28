package com.hadialaoui.authenticationServiceJWT.constants;

public interface SecurityConstants {
	String SECRET = "hadialaoui+a.hadialaoui@gmail.com+jwt+secret+key";
	long  Expiration_TIME = 868_000_000;
	String TOKEN_PREFIX = "Bearer ";
	String JWT_HEADER_STRING = "Authorization";
}
