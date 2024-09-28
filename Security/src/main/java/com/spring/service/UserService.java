package com.spring.service;

import java.util.List;

import com.spring.dto.UserRequest;
import com.spring.model.UserDtls;

public interface UserService {
	public String login(UserRequest request);
	public List<UserDtls> getUserDtls();
}