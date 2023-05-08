package com.casa.sysmap.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.casa.sysmap.models.Like;
import com.casa.sysmap.models.Post;
import com.casa.sysmap.models.dto.RequestPost;

public interface PostService {

	RequestPost createPost(Post post);

	Post createComment(Post post);

	Post addLike(Like like, UUID id);

	Post removeComment(Post post);

	Post removeLike(Like like, UUID id);

	RequestPost updatePost(UUID id, Post post);

	void deletePostById(UUID id);

	Post findPostById(UUID id);

	RequestPost findPostDtoById(UUID id);

	List<RequestPost> findAllPosts();

	Page<RequestPost> findAllPageablePosts(Pageable pageable);

	Integer totalComments(UUID id);

	Integer totalLikes(UUID id);

}
