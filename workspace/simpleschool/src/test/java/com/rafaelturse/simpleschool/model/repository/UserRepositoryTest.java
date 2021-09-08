package com.rafaelturse.simpleschool.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rafaelturse.simpleschool.model.entity.UserORM;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Test
	public void mustVerifyEmailExistence() {
		// scenery
		UserORM user = UserORM.builder().name("john").email("john@school.com").build();
		repository.save(user);

		// action
		boolean result = repository.existsByEmail("john@school.com");

		// verification
		Assertions.assertThat(result).isTrue();
	}
}
