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
import org.springframework.web.bind.annotation.RestController;

import com.casa.sysmap.models.User;
import com.casa.sysmap.models.dto.RequestUser;
import com.casa.sysmap.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<RequestUser> createEntity(@Valid @RequestBody User user) {
		return new ResponseEntity<RequestUser>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<RequestUser> updateEntity(@Valid @PathVariable UUID id, @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(id, user));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {
		userService.deleteUserById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<RequestUser> findEntityById(@PathVariable UUID id) {
		return ResponseEntity.ok(userService.findDtoUserById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<RequestUser>> findAllEntities() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<RequestUser>> findAllPageableEntities(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(userService.findAllPageableUsers(pageable));
	}

	@PostMapping("/addFriend")
	public ResponseEntity<List<RequestUser>> addEntityFriends(@RequestBody User user) {
		return ResponseEntity.ok(userService.addFriend(user));
	}

	@PutMapping("/removeFriend")
	public ResponseEntity<List<RequestUser>> removeEntityFriends(@RequestBody User user) {
		return ResponseEntity.ok(userService.removeFriend(user));
	}

	@PostMapping("/addPhoto")
	public ResponseEntity<String> addEntityPhoto(@RequestBody User user) {
		userService.addPhoto(user);
		return ResponseEntity.ok("Photo successfully added.");
	}

}
