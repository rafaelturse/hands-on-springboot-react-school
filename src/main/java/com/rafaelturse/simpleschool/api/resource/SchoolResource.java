package com.rafaelturse.simpleschool.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelturse.simpleschool.api.dto.SchoolDTO;
import com.rafaelturse.simpleschool.model.entity.SchoolORM;
import com.rafaelturse.simpleschool.service.SchoolService;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;
import com.rafaelturse.simpleschool.utils.message.MessageUtils;

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
	
	private SchoolDTO converter(SchoolORM orm) {
		SchoolDTO school = SchoolDTO.builder()
				.id(orm.getId())
				.school(orm.getName())
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
	
	@GetMapping("{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) {
		return service.findById(id).map(i -> {
			try {
				return ResponseEntity.ok(converter(i));
			} catch (BusinessRuleException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity(MessageUtils.ERROR_MESSAGE_GRADES_NOT_FOUND, HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.findById(id).map(i -> {
			service.delete(i);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity(MessageUtils.ERROR_MESSAGE_GRADES_NOT_FOUND, HttpStatus.BAD_REQUEST));
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody SchoolDTO dto) {
		return service.findById(id).map(i -> {
			try {
				SchoolORM school = converter(dto);
				school.setId(i.getId());
				service.update(school);
				return ResponseEntity.ok(school);
			} catch (BusinessRuleException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity(MessageUtils.ERROR_MESSAGE_GRADES_NOT_FOUND, HttpStatus.BAD_REQUEST));
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
