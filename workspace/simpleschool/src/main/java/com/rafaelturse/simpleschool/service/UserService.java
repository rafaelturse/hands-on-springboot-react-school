package com.rafaelturse.simpleschool.service;

import java.util.Optional;

import com.rafaelturse.simpleschool.model.entity.UserORM;

public interface UserService {

	UserORM authenticate(String email, String password);

	UserORM save(UserORM user);
	
	void validateEmail(String email);
	
	Optional<UserORM> findById(Long id);
}
