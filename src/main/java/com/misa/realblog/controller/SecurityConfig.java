package com.misa.realblog.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("MANAGER", "EMPLOYEE", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("MANAGER", "EMPLOYEE", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/users").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/users").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/posts").hasAnyRole("MANAGER", "EMPLOYEE", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/posts/**").hasAnyRole("MANAGER", "EMPLOYEE", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/posts").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/posts/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/posts").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/posts/**").hasRole("ADMIN")
		
		.antMatchers("/").anonymous()
		.antMatchers("/registration").anonymous()
		
		.antMatchers("/login").anonymous()
//		.anyRequest().authenticated()
//		.anyRequest().denyAll()
//		.and().httpBasic()
//		.and().anonymous().disable()
		.and().csrf().disable()
		.formLogin().permitAll()
		.and().logout().permitAll()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService()); //set the custom user details service
		auth.setPasswordEncoder(passwordEncode()); //set the password encoder - bcrypt
		return auth;
	}
}
