package com.rafaelturse.simpleschool.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelturse.simpleschool.api.dto.UserDTO;
import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.service.UserService;
import com.rafaelturse.simpleschool.utils.exception.AuthenticationException;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;

@RestController
@RequestMapping("/api/users")
public class UserResource {

	@Autowired
	private UserService service;

	@PostMapping("/authenticate")
	public ResponseEntity authenticate(@RequestBody UserDTO dto) {
		try {
			UserORM authenticated = service.authenticate(dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(authenticated);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity save(@RequestBody UserDTO dto) {
		UserORM user = UserORM.builder().name(dto.getName()).email(dto.getEmail()).password(dto.getPassword()).build();

		try {
			UserORM savedUser = service.save(user);
			return new ResponseEntity(savedUser, HttpStatus.CREATED);
		} catch (BusinessRuleException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
