package com.casa.sysmap.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.sysmap.exceptions.ObjectNotFoundException;
import com.casa.sysmap.models.Like;
import com.casa.sysmap.models.Post;
import com.casa.sysmap.models.User;
import com.casa.sysmap.models.dto.RequestPost;
import com.casa.sysmap.models.dto.RequestUser;
import com.casa.sysmap.repositories.PostRepository;
import com.casa.sysmap.services.LikeService;
import com.casa.sysmap.services.PostService;
import com.casa.sysmap.services.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private LikeService likeService;

	private RequestPost postToDtoPost(Post post) {
		RequestUser userDto = new RequestUser(post.getUser().getId(), post.getUser().getPhoto(),
				post.getUser().getName());
		RequestPost dto = new RequestPost(post.getId(), post.getText(), userDto);
		return dto;
	}

	@Override
	public RequestPost createPost(Post post) {
		User user = userService.findUserById(post.getUser().getId());
		post.setUser(user);
		postRepository.save(post);
		RequestPost dto = postToDtoPost(post);
		return dto;
	}

	@Override
	public Post createComment(Post post) {
		Post newPost = findPostById(post.getId());
		User user = userService.findUserById(post.getPost().getUser().getId());
		Post comment = new Post(post.getPost().getText(), user);
		createPost(comment);
		newPost.getComments().add(comment);
		newPost.setId(post.getId());
		return postRepository.save(newPost);
	}

	@Override
	public Post removeComment(Post post) {
		Post targetPost = findPostById(post.getId());
		Post comment = findPostById(post.getPost().getId());
		targetPost.getComments().remove(comment);
		deletePostById(comment.getId());
		targetPost.setId(post.getId());
		return postRepository.save(targetPost);
	}

	@Override
	public RequestPost updatePost(UUID id, Post post) {
		Post foundPost = findPostById(id);
		post.setId(foundPost.getId());
		return createPost(post);
	}

	@Override
	public void deletePostById(UUID id) {
		Post post = findPostById(id);
		postRepository.deleteById(post.getId());
	}

	@Override
	public Post findPostById(UUID id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found."));
		return post;
	}

	@Override
	public RequestPost findPostDtoById(UUID id) {
		Post post = findPostById(id);
		RequestPost dto = postToDtoPost(post);
		return dto;
	}

	@Override
	public List<RequestPost> findAllPosts() {
		List<Post> posts = postRepository.findAll();
		List<RequestPost> dtoPosts = posts.stream().map(dto -> postToDtoPost(dto)).collect(Collectors.toList());
		return dtoPosts;
	}

	@Override
	public Page<RequestPost> findAllPageablePosts(Pageable pageable) {
		Page<Post> posts = postRepository.findAll(pageable);
		Page<RequestPost> dtoPosts = posts.map(post -> postToDtoPost(post));
		return dtoPosts;
	}

	@Override
	public Post addLike(Like like, UUID id) {
		Post post = findPostById(id);
		User user = userService.findUserById(like.getUser().getId());
		Like addLike = new Like(user);
		post.getLikes().add(addLike);
		likeService.createLike(addLike);
		post.setId(id);
		return postRepository.save(post);
	}

	@Override
	public Post removeLike(Like like, UUID id) {
		Post post = findPostById(id);
		Like removedLike = likeService.findLikeById(like.getId());
		post.getLikes().remove(removedLike);
		likeService.deleteLikeById(removedLike.getId());
		post.setId(id);
		return postRepository.save(post);
	}

	@Override
	public Integer totalComments(UUID id) {
		Post foundedPost = findPostById(id);
		return foundedPost.getComments().size();
	}

	@Override
	public Integer totalLikes(UUID id) {
		Post foundedPost = findPostById(id);
		return foundedPost.getLikes().size();
	}

}
