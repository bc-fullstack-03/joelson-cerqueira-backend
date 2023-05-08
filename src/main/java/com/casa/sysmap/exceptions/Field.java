package com.casa.sysmap.exceptions;

import lombok.Data;

@Data
public class Field {

	private String fieldName;
	private String fieldError;

	public Field(String fieldName, String fieldError) {
		super();
		this.fieldName = fieldName;
		this.fieldError = fieldError;
	}

}
