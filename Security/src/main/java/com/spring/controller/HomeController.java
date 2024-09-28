package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserRequest;
import com.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<?> getDetails(HttpServletRequest request) {
		String id = request.getSession().getId();
		return new ResponseEntity<>("Hello ,Welcome to Becoder Dashboard"+id, HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userReqeust) {
		String token = userService.login(userReqeust);
		if (token == null) {
			return new ResponseEntity<>("invalid credentials", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
		return new ResponseEntity<>(userService.getUserDtls(), HttpStatus.OK);
	}
}

/*
 * 
 * public and private Cryptography
 * 
 * 1. public key can distribute  and  share with anyone
 * 2.Private key not shareable
 * 3.All ShareAble data storing in encryption format it can be decrypty with particular private key
 * 4.JWT is working on public and private key concept
 * 
 * Stateless and Stateful
 * 
 * Stateless: we cannot store state in DB or any file, JwT is working on Stateless mechanism
 * 
 * Stateful: State are storing in DB or file.
 * 
 * JWT stands for Json Web token . It is a normal String 
 * it is combination of 3 parts-Header , Payload, signatuer 
 * 
 * Token eyjhbggdggsjgsjgsjagjlsjglsjsjlgjsjgjsjgsj
 * 
 * 
 * 
 * 
 */

