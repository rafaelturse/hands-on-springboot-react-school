package com.rafaelturse.simpleschool.service;

import com.rafaelturse.simpleschool.model.entity.UserORM;

public interface UserService {

	UserORM authenticate(String email, String password);

	UserORM save(UserORM user);

	void validateEmail(String email);
}
