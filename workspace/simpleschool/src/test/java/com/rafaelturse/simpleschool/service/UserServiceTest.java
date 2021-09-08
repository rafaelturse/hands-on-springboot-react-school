package com.rafaelturse.simpleschool.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.repository.UserRepository;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	UserService service;

	@Autowired
	UserRepository repository;

	@Test(expected = Test.None.class)
	public void mustValidateEmail() {
		// scenery
		repository.deleteAll();

		// action
		service.validateEmail("john@school.com");
	}

	@Test(expected = BusinessRuleException.class)
	public void mustTrowValidateErrorWhenEmailExists() {
		// scenery
		UserORM user = UserORM.builder().name("john").email("john@school.com").build();
		repository.save(user);

		// action
		service.validateEmail("john@school.com");
	}
}
