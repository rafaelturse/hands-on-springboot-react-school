package com.rafaelturse.simpleschool.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.repository.UserRepository;
import com.rafaelturse.simpleschool.service.imp.UserServiceImp;
import com.rafaelturse.simpleschool.utils.exception.AuthenticationException;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;
import com.rafaelturse.simpleschool.utils.message.MessageUtils;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserServiceTest {

	@SpyBean
	UserServiceImp service;

	@MockBean
	UserRepository repository;

	public static UserORM setUser() {
		// scenery
		return UserORM.builder().id(1l).name("john").password("123").email("john@school.com").build();
	}

	@Test(expected = Test.None.class)
	public void mustPersistUser() {
		// scenery
		Mockito.doNothing().when(service).validateEmail(Mockito.anyString());
		UserORM user = setUser();

		Mockito.when(repository.save(Mockito.any(UserORM.class))).thenReturn(user);

		// action
		UserORM savedUser = service.save(new UserORM());

		// verification
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isEqualTo(1l);
		Assertions.assertThat(savedUser.getName()).isEqualTo(user.getName());
		Assertions.assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
		Assertions.assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
	}

	@Test(expected = BusinessRuleException.class)
	public void mustNotPersistUserWhenEmailAlreadyExists() {
		// scenery
		UserORM user = setUser();
		Mockito.doThrow(BusinessRuleException.class).when(service).validateEmail(user.getEmail());

		// action
		service.save(user);

		// verification
		Mockito.verify(repository, Mockito.never()).save(user);
	}

	@Test(expected = Test.None.class)
	public void MustAuthenticateUserSuccessfully() {
		// scenery
		UserORM user = setUser();
		Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

		// action
		UserORM result = service.authenticate(user.getEmail(), user.getPassword());

		// verification
		Assertions.assertThat(result).isNotNull();
	}

	@Test
	public void MustThrowErrorWhenFailToFindAuthenticateUser() {
		// scenery
		UserORM user = setUser();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// action
		Throwable exception = Assertions
				.catchThrowable(() -> service.authenticate(user.getEmail(), user.getPassword()));
		Assertions.assertThat(exception).isInstanceOf(AuthenticationException.class)
				.hasMessage(MessageUtils.ERROR_MESSAGE_USER_NOT_FOUND_BY_EMAIL);
	}

	@Test
	public void MustThrowErrorWhenWrongPassword() {
		// scenery
		UserORM user = setUser();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		// action
		Throwable exception = Assertions.catchThrowable(() -> service.authenticate(user.getEmail(), "wrongPassword"));
		Assertions.assertThat(exception).isInstanceOf(AuthenticationException.class)
				.hasMessage(MessageUtils.ERROR_MESSAGE_WRONG_PASSWORD);
	}

	@Test(expected = Test.None.class)
	public void mustValidateEmail() {
		// scenery
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// action
		service.validateEmail("john@school.com");
	}

	@Test(expected = BusinessRuleException.class)
	public void mustThrowValidateErrorWhenEmailExists() {
		// scenery
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// action
		service.validateEmail("john@school.com");
	}
}