package com.hadialaoui.authenticationServiceJWT.filters;

import java.io.IOException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadialaoui.authenticationServiceJWT.constants.SecurityConstants;
import com.hadialaoui.authenticationServiceJWT.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private ObjectMapper objectMapper; 
	AuthenticationManager authenticationManager;
	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user = null;
		try {
			System.out.println("username *********************" + request.getParameter("username"));
			//user = new ObjectMapper().readValue(request.getInputStream(), User.class); 
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	return authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getParameter("username"),request.getParameter("password")));
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetails 
		springUser = (UserDetails) authResult.getPrincipal();
		
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
		String base64Key = Encoders.BASE64.encode(SecurityConstants.SECRET.getBytes());
		String jwtToken = Jwts.builder()
							.setSubject(springUser.getUsername())
							.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.Expiration_TIME))
							.signWith(SignatureAlgorithm.HS256 , base64Key)
							.claim("roles",authResult.getAuthorities())
							.compact();
		
		response.addHeader(SecurityConstants.JWT_HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);
	}

	
}
