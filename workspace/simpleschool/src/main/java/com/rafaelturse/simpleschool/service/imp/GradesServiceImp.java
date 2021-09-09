package com.rafaelturse.simpleschool.service.imp;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelturse.simpleschool.model.entity.GradesORM;
import com.rafaelturse.simpleschool.model.repository.GradesRepository;
import com.rafaelturse.simpleschool.service.GradesService;

@Service
public class GradesServiceImp implements GradesService {

	@Autowired
	private GradesRepository repository;

	public GradesServiceImp(GradesRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public GradesORM save(GradesORM grades) {
		validate(grades);
		return repository.save(grades);
	}

	@Override
	@Transactional
	public GradesORM update(GradesORM grades) {
		Objects.requireNonNull(grades.getId());
		validate(grades);
		return repository.save(grades);
	}

	@Override
	@Transactional
	public void delete(GradesORM grades) {
		Objects.requireNonNull(grades.getId());
		repository.delete(grades);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GradesORM> find(GradesORM gradesFilter) {
		Example example = Example.of(gradesFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return repository.findAll(example);
	}

	@Override
	public void validate(GradesORM grades) {
		/*
		 * if (grades.getStudent() == null ||
		 * grades.getStudent().getName().trim().equals("")) { throw new
		 * BusinessRuleException(""); }
		 * 
		 * if (grades.getSchool() == null ||
		 * grades.getSchool().getName().trim().equals("")) { throw new
		 * BusinessRuleException(""); }
		 * 
		 * if (grades.getSubject() == null ||
		 * grades.getSubject().getName().trim().equals("")) { throw new
		 * BusinessRuleException(""); }
		 * 
		 * if (grades.getGrade1() == null) { throw new BusinessRuleException(""); }
		 * 
		 * if (grades.getGrade2() == null) { throw new BusinessRuleException(""); }
		 * 
		 * if (grades.getGrade3() == null) { throw new BusinessRuleException(""); }
		 * 
		 * if (grades.getGrade4() == null) { throw new BusinessRuleException(""); }
		 */
	}
}
