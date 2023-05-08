package com.casa.sysmap.exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Wrong {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private Integer code;
	private String message;
	private String path;
	private List<Field> fields = new ArrayList<>();

	public Wrong(LocalDate date, Integer code, String message, String path) {
		super();
		this.date = date;
		this.code = code;
		this.message = message;
		this.path = path;
	}

	public void setField(String name, String field) {
		fields.add(new Field(name, field));
	}

}
