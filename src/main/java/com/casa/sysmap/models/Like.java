package com.casa.sysmap.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id = UUID.randomUUID();
	private User user;
	private List<User> users = new ArrayList<>();

	public Like(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

}
