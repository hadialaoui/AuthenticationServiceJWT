package com.hadialaoui.authenticationServiceJWT.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hadialaoui.authenticationServiceJWT.filters.JWTAuthenticationFilter;
import com.hadialaoui.authenticationServiceJWT.filters.JWTAutorizationFilter;


/**
 * @author Abdelhakim
 *
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		//////////////////////////////////////Memory Authentication \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		/*auth.inMemoryAuthentication()
		.withUser("hadialaoui").password("hadialaoui").roles("USER")
		.and()
		.withUser("admin").password("admin").roles("ADMIN");*/
		
		
		//////////////////////////////// Default Authentication (h2) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		/*auth.jdbcAuthentication()
		.dataSource(dataSource).withDefaultSchema()
		.withUser(
				User.withUsername("admin2")
				.password("admin2")
				.roles("ADMIN"))
		.withUser(
				User.withUsername("hadialaoui2")
				.password("hadialaoui2") 
				.roles("USER"));*/
		
		//////////////////Default Tables USERS/AUTHORITIERS Authentication (h2) \\\\\\\\\\\\\\\\\\\\\
		/*auth.jdbcAuthentication()
		.dataSource(dataSource);*/
		
		//////////////////custom Tables myusers/myauthorities Authentication (h2) \\\\\\\\\\\\\\\\\\\
		/*auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from myusers where username = ? ")
		.authoritiesByUsernameQuery("select username, authority from myauthorities where username = ?");*/
		
		/////////////////////////////////  Authentication (MySQL) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		auth.userDetailsService(userDetailsService);//.passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/dashboardAdmins").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.GET,"/PageUsers").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
			.antMatchers(HttpMethod.POST,"/login").permitAll()
			//.antMatchers("/dashboardAdmins").permitAll()
			.anyRequest().authenticated()
			.and()
            .logout().logoutUrl("/logout").invalidateHttpSession(true)
            ;
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilterBefore(new JWTAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

    
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
    
}
