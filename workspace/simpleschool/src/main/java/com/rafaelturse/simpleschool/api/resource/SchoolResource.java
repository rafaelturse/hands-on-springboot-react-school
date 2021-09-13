package com.rafaelturse.simpleschool.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelturse.simpleschool.service.SchoolService;

@SuppressWarnings({ "rawtypes" })
@RestController
@RequestMapping("/api/school")
public class SchoolResource {

	@Autowired
	private SchoolService service;

	@GetMapping
	public ResponseEntity find() {
		return ResponseEntity.ok(service.findAll());
	}
}
