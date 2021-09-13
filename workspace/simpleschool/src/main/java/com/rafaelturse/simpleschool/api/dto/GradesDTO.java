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
	private Integer grade1;
	private Integer grade2;
	private Integer grade3;
	private Integer grade4;
}
