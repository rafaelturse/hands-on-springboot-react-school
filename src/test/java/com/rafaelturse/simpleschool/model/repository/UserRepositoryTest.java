package com.rafaelturse.simpleschool.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rafaelturse.simpleschool.model.entity.UserORM;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Autowired
	TestEntityManager entityManager;

	public static UserORM setUser() {
		// scenery
		return UserORM.builder().name("john").password("123").email("john@school.com").build();
	}

	@Test
	public void mustVerifyEmailExistence() {
		// scenery
		entityManager.persist(setUser());

		// action
		boolean result = repository.existsByEmail("john@school.com");

		// verification
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void mustVerifyEmailNonExistence() {
		// action
		boolean result = repository.existsByEmail("john@school.com");

		// verification
		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void mustPersistUser() {
		// action
		UserORM savedUser = repository.save(setUser());

		// verification
		Assertions.assertThat(savedUser.getId()).isNotNull();
	}

	@Test
	public void mustGetUserByEmail() {
		// scenery
		UserORM user = setUser();
		entityManager.persist(user);

		// action
		Optional<UserORM> result = repository.findByEmail(user.getEmail());

		// verification
		Assertions.assertThat(result.isPresent()).isTrue();
	}

	@Test
	public void mustReturnEmptyWhenCantFindUserByEmail() {
		// action
		Optional<UserORM> result = repository.findByEmail(setUser().getEmail());

		// verification
		Assertions.assertThat(result.isPresent()).isFalse();
	}
}