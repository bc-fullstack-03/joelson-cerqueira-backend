package com.casa.sysmap.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.casa.sysmap.models.Like;
import com.casa.sysmap.models.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestPost implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id = UUID.randomUUID();
	private String text;
	private RequestUser user;
	private List<Like> likes = new ArrayList<>();
	private List<Post> comments = new ArrayList<>();

	public RequestPost(UUID id, String text, RequestUser user) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
	}

}
