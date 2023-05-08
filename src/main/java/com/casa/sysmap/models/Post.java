package com.casa.sysmap.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id = UUID.randomUUID();
	private String text;
	private User user;
	private Post post;
	private Like like;
	private List<Like> likes = new ArrayList<>();
	private List<Post> comments = new ArrayList<>();

	public Post(String text, User user) {
		this.text = text;
		this.user = user;
	}

}
