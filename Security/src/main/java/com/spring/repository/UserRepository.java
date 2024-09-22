package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.spring.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls,Integer>{
	UserDtls findByUsername(String username);
}
