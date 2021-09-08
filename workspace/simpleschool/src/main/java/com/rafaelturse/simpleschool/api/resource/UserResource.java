package com.rafaelturse.simpleschool.api.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@GetMapping("/")
	public String helloWorld( ) {
		return "helloWorld";
	}
}
