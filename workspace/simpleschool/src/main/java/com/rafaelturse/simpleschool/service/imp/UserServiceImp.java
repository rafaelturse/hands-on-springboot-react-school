package com.rafaelturse.simpleschool.service.imp;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.repository.UserRepository;
import com.rafaelturse.simpleschool.service.UserService;
import com.rafaelturse.simpleschool.utils.exception.AuthenticationException;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;
import com.rafaelturse.simpleschool.utils.message.MessageUtils;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repository;

	public UserServiceImp(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UserORM authenticate(String email, String password) {
		Optional<UserORM> user = repository.findByEmail(email);

		if (!user.isPresent()) {
			throw new AuthenticationException(MessageUtils.ERROR_MESSAGE_USER_NOT_FOUND_BY_EMAIL);
		}

		if (!user.get().getPassword().equals(password)) {
			throw new AuthenticationException(MessageUtils.ERROR_MESSAGE_WRONG_PASSWORD);
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
			throw new BusinessRuleException(MessageUtils.ERROR_MESSAGE_ALREADY_REGISTERED_USER_BY_EMAIL);
		}
	}

}
