package com.rafaelturse.simpleschool.api.resource;

import java.util.List;
import java.util.Optional;

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

import com.rafaelturse.simpleschool.api.dto.GradesDTO;
import com.rafaelturse.simpleschool.model.entity.GradesORM;
import com.rafaelturse.simpleschool.model.entity.SchoolORM;
import com.rafaelturse.simpleschool.model.entity.UserORM;
import com.rafaelturse.simpleschool.model.enumeration.SubjectEnum;
import com.rafaelturse.simpleschool.service.GradesService;
import com.rafaelturse.simpleschool.service.SchoolService;
import com.rafaelturse.simpleschool.service.UserService;
import com.rafaelturse.simpleschool.utils.exception.BusinessRuleException;
import com.rafaelturse.simpleschool.utils.message.MessageUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/grades")
public class GradesResource {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private UserService userService;

	@Autowired
	private GradesService service;

	private Optional<SchoolORM> findSchool(Long id) {
		try {
			return schoolService.findById(id);
		} catch (BusinessRuleException e) {
			throw new BusinessRuleException(MessageUtils.ERROR_MESSAGE_SCHOOL_NOT_FOUND);
		}
	}
	
	private SchoolORM findSchool(String school) {
		return schoolService.find(SchoolORM.builder().name(school).build()).get(0);
	}

	private Optional<UserORM> findUser(Long i) {
		try {
			return userService.findById(i);
		} catch (BusinessRuleException e) {
			throw new BusinessRuleException(MessageUtils.ERROR_MESSAGE_USER_NOT_FOUND_BY_ID);
		}
	}

	private GradesORM converter(GradesDTO dto) {
		GradesORM grades = GradesORM.builder()
				.id(dto.getId())
				.user(findUser(dto.getUser()).get())
				.subject(dto.getSubject())
				.school(findSchool(dto.getSchool()).get())
				.student(dto.getStudent())
				.grade1(dto.getGrade1())
				.grade2(dto.getGrade2())
				.grade3(dto.getGrade3())
				.grade4(dto.getGrade4())
				.build();
		return grades;
	}
	
	private GradesDTO converter(GradesORM orm) {
		GradesDTO grades = GradesDTO.builder()
				.id(orm.getId())
				.user(orm.getUser().getId())
				.subject(orm.getSubject())
				.school(orm.getSchool().getId())
				.student(orm.getStudent())
				.grade1(orm.getGrade1())
				.grade2(orm.getGrade2())
				.grade3(orm.getGrade3())
				.grade4(orm.getGrade4())
				.build();
		return grades;
	}

	@PostMapping
	public ResponseEntity save(@RequestBody GradesDTO dto) {
		try {
			GradesORM grades = converter(dto);

			GradesORM savedGrades = service.save(grades);
			return new ResponseEntity(savedGrades, HttpStatus.CREATED);
		} catch (BusinessRuleException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody GradesDTO dto) {
		return service.findById(id).map(i -> {
			try {
				GradesORM grades = converter(dto);
				grades.setId(i.getId());
				service.update(grades);
				return ResponseEntity.ok(grades);
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

	@GetMapping
	public ResponseEntity find(
			@RequestParam(value ="school" , required = false) String school,
			@RequestParam(value = "student", required = false) String student,
			@RequestParam(value = "subject", required = false) Integer subject,
			@RequestParam("user") Long user) {
		GradesORM gradesFilter = new GradesORM();
		
		if ((null != school)) {
			gradesFilter.setSchool(findSchool(school));
		}
		
		if ((null != student)) {
			gradesFilter.setStudent(student);
		}
		
		if (null != subject) {
			gradesFilter.setSubject(SubjectEnum.valueOfId(subject));
		}
		
		gradesFilter.setUser(findUser(user).get());
	
		List<GradesORM> grades = service.find(gradesFilter);
		return ResponseEntity.ok(grades);
	}
}
