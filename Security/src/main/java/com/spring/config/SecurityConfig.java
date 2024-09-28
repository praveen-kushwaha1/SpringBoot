package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 * http.csrf(customizer -> customizer.disable());
		 * //http.authorizeHttpRequests(request ->
		 * request.anyRequest().authenticated());
		 * http.authorizeHttpRequests(request->request.requestMatchers("login",
		 * "register").permitAll().anyRequest().authenticated());
		 * //http.formLogin(Customizer.withDefaults());
		 * http.httpBasic(Customizer.withDefaults()); return http.build();
		 */
		
		http.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request.requestMatchers("login", "register").permitAll()
				.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();

	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}












/*
 * 
 * 
 * In Spring Boot, there are several types of authentication mechanisms you can
 * implement, depending on your application's requirements. Here’s an overview
 * of the most common types:
 * 
 * 1. Basic Authentication Description: Involves sending the username and
 * password with each request, typically encoded in Base64. Implementation: Use
 * Spring Security with basic auth configuration.
 * 
 * 2. Form-Based Authentication Description: Users submit their credentials via
 * a web form. Implementation: Spring Security can be configured to handle form
 * submissions for authentication.
 * 
 * 3. JWT (JSON Web Token) Authentication Description: Uses tokens that are
 * issued after user login. Tokens are sent with each request for authorization.
 * Implementation: Typically implemented with Spring Security and libraries like
 * jjwt for token generation and validation.
 * 
 * 4. OAuth 2.0 Description: An authorization framework that allows third-party
 * services to exchange information without sharing credentials. Implementation:
 * Spring Security provides support for OAuth 2.0, allowing integration with
 * providers like Google, Facebook, etc.
 * 
 * 5. SAML (Security Assertion Markup Language) Description: An XML-based
 * framework for exchanging authentication and authorization data between
 * parties, particularly for single sign-on (SSO). Implementation: Spring
 * Security SAML extension can be used for this purpose.
 * 
 * 6. LDAP (Lightweight Directory Access Protocol) Authentication Description:
 * Uses an LDAP server for authentication, suitable for enterprise applications.
 * Implementation: Spring Security can be configured to authenticate users
 * against an LDAP server.
 * 
 * 7. Token-Based Authentication Description: Users receive a token upon
 * successful login, which is used for subsequent requests. Implementation:
 * Similar to JWT but can use other formats (like OAuth tokens).
 * 
 * 8. Two-Factor Authentication (2FA) Description: Requires two forms of
 * identification before access is granted. Implementation: Can be integrated
 * into existing Spring Security configurations with additional verification
 * steps. Conclusion
 * 
 */