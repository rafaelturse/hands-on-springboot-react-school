package com.rafaelturse.simpleschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.repository.UserRepository;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserORM authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserORM save(UserORM user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new BusinessRuleException("There is already a registered user with this email");
		}
	}

}
