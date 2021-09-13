package com.rafaelturse.simpleschool.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelturse.simpleschool.api.dto.SchoolDTO;
import com.rafaelturse.simpleschool.model.entity.SchoolORM;
import com.rafaelturse.simpleschool.service.SchoolService;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/school")
public class SchoolResource {

	@Autowired
	private SchoolService service;
	
	private SchoolORM converter(SchoolDTO dto) {
		SchoolORM school = SchoolORM.builder()
				.id(dto.getId())
				.name(dto.getSchool())
				.build();
		return school;
	}

	@GetMapping
	public ResponseEntity find(@RequestParam(value = "school", required = false) String school) {
		SchoolORM schoolFilter = new SchoolORM();

		if ((null != school)) {
			schoolFilter.setName(school);
		}

		return ResponseEntity.ok(service.find(schoolFilter));
	}

	@PostMapping
	public ResponseEntity save(@RequestBody SchoolDTO dto) {
		try {
			SchoolORM school = converter(dto);

			SchoolORM savedSchool = service.save(school);
			return new ResponseEntity(savedSchool, HttpStatus.CREATED);
		} catch (BusinessRuleException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
