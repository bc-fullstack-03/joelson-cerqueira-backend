package com.casa.sysmap.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.casa.sysmap.models.dto.RequestUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id = UUID.randomUUID();
	private String photo;
	@NotBlank(message = "blank values are not accept")
	private String name;
	@NotBlank(message = "you need a password")
	@Min(value = 8, message = "password must contain more than 7 characters.")
	private String password;
	@NotBlank(message = "fill in the email field please.")
	@Email(message = "write a valid email please.")
	private String email;
	private User user;
	private List<RequestUser> followers = new ArrayList<>();

	public User(String photo, String name, String password, String email) {
		this.photo = photo;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public User(UUID id, String photo, String name) {
		this.id = id;
		this.photo = photo;
		this.name = name;
	}

}
