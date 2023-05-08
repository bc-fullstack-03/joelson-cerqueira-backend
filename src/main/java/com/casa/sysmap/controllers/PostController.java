package com.casa.sysmap.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.sysmap.models.Like;
import com.casa.sysmap.models.Post;
import com.casa.sysmap.models.dto.RequestPost;
import com.casa.sysmap.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/create")
	public ResponseEntity<RequestPost> createEntity(@Valid @RequestBody Post post) {
		return new ResponseEntity<RequestPost>(postService.createPost(post), HttpStatus.CREATED);
	}

	@PostMapping("/comment/create")
	public ResponseEntity<String> createCommentEntity(@RequestBody Post comment) {
		postService.createComment(comment);
		return new ResponseEntity<String>("Comment created successfully.", HttpStatus.CREATED);
	}

	@PostMapping("/add/like")
	public ResponseEntity<String> addLike(@RequestBody Like like, @RequestParam(name = "id") UUID id) {
		postService.addLike(like, id);
		return new ResponseEntity<String>("Like successfully added", HttpStatus.CREATED);
	}

	@PutMapping("/comment/remove")
	public ResponseEntity<String> removeCommentEntity(@RequestBody Post comment) {
		postService.removeComment(comment);
		return new ResponseEntity<String>("Comment removed successfully.", HttpStatus.OK);
	}

	@PutMapping("/remove/like")
	public ResponseEntity<String> removeLike(@RequestBody Like like, @RequestParam(name = "id") UUID id) {
		postService.removeLike(like, id);
		return new ResponseEntity<String>("Like successfully removed", HttpStatus.OK);
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<RequestPost> updateEntity(@Valid @PathVariable UUID id, @RequestBody Post post) {
		return ResponseEntity.ok(postService.updatePost(id, post));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {
		postService.deletePostById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<RequestPost> findEntityById(@PathVariable UUID id) {
		return ResponseEntity.ok(postService.findPostDtoById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<RequestPost>> findAllEntities() {
		return ResponseEntity.ok(postService.findAllPosts());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<RequestPost>> findAllPageableEntities(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(postService.findAllPageablePosts(pageable));
	}

	@GetMapping("/total/comments")
	public ResponseEntity<Integer> totalComments(@RequestParam(name = "id") UUID id) {
		return ResponseEntity.ok(postService.totalComments(id));
	}

	@GetMapping("/total/likes")
	public ResponseEntity<Integer> totalLikes(@RequestParam(name = "id") UUID id) {
		return ResponseEntity.ok(postService.totalLikes(id));
	}

}
