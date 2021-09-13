package com.rafaelturse.simpleschool.api.dto;

import com.rafaelturse.simpleschool.model.enumeration.SubjectEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GradesDTO {

	private Long id;
	private SubjectEnum subject;
	private Long user;
	private String student;
	private Long school;
	private Float grade1;
	private Float grade2;
	private Float grade3;
	private Float grade4;
}
