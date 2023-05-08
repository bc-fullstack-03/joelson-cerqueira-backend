package com.casa.sysmap.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	private String photo = "";
	private String name;
	private List<RequestUser> followers = new ArrayList<>();

	public RequestUser(UUID id, String photo, String name) {
		super();
		this.id = id;
		this.photo = photo;
		this.name = name;
	}

	public RequestUser(String photo, String name) {
		super();
		this.photo = photo;
		this.name = name;
	}

}
