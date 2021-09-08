package com.rafaelturse.simpleschool.model.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum SubjectEnum {

	PHILOSOPHY(1, "Philosophy"),
	MATHEMATICS(2, "Mathmatics"),
	SPANISH(3, "Spanish"),
	ENGLISH(4, "English"),
	CHEMICAL(5, "Chemical"),
	PHYSICAL(6, "Physical"),
	STORY(7, "Story"),
	MUSIC(8, "Music"),
	PHYSICAL_EDUCATION(9, "Physical Education"),
	SOCIOLOGY(10, "Sociology"),
	GEOGRAPHY(11, "Geography"),
	ART(12, "Art");
	
	private static final Map<Integer, SubjectEnum> LOOKUP = new HashMap<>();

	static {
		for (SubjectEnum e : SubjectEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final int id;
	private final String name;

	SubjectEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static SubjectEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}