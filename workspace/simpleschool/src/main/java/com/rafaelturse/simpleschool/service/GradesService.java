package com.rafaelturse.simpleschool.service;

import java.util.List;

import com.rafaelturse.simpleschool.model.entity.GradesORM;

public interface GradesService {

	GradesORM save(GradesORM grades);

	GradesORM update(GradesORM grades);

	void delete(GradesORM grades);

	List<GradesORM> find(GradesORM gradesFilter);

	void validate(GradesORM grandes);
}
