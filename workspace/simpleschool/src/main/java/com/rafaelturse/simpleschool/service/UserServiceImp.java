package com.rafaelturse.simpleschool.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.repository.UserRepository;
import com.rafaelturse.simpleschool.utils.exception.AuthenticationException;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserORM authenticate(String email, String password) {
		Optional<UserORM> user = repository.findByEmail(email);

		if (!user.isPresent()) {
			throw new AuthenticationException("User not found for this email");
		}

		if (user.get().getPassword().equals(password)) {
			throw new AuthenticationException("Wrong password");
		}

		return user.get();
	}

	@Override
	@Transactional
	public UserORM save(UserORM user) {
		validateEmail(user.getEmail());
		return repository.save(user);
	}

	@Override
	public void validateEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new BusinessRuleException("There is already a registered user with this email");
		}
	}

}
