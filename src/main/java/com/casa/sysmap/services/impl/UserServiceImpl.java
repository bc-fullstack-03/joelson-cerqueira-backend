package com.casa.sysmap.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.casa.sysmap.exceptions.ObjectNotFoundException;
import com.casa.sysmap.models.User;
import com.casa.sysmap.models.dto.RequestUser;
import com.casa.sysmap.repositories.UserRepository;
import com.casa.sysmap.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private RequestUser userToDtoUser(User user) {
		RequestUser dto = new RequestUser(user.getId(), user.getPhoto(), user.getName());
		dto.setFollowers(user.getFollowers());
		return dto;
	}

	@Override
	public RequestUser createUser(User user) {
		if (!userRepository.findUserByEmail(user.getEmail()).isEmpty()) {
			throw new IllegalArgumentException("This email already exists.");
		}
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);
		RequestUser dto = userToDtoUser(userRepository.save(user));
		return dto;
	}

	@Override
	public RequestUser updateUser(UUID id, User user) {
		RequestUser foundUser = userToDtoUser(findUserById(id));
		user.setId(foundUser.getId());
		foundUser = userToDtoUser(user);
		userRepository.save(user);
		return foundUser;
	}

	@Override
	public void deleteUserById(UUID id) {
		User user = findUserById(id);
		userRepository.deleteById(user.getId());
	}

	@Override
	public User findUserById(UUID id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found."));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email).get();
		return user;
	}

	@Override
	public User getUser(String email) {
		return userRepository.findUserByEmail(email).get();
	}

	@Override
	public RequestUser findDtoUserById(UUID id) {
		User user = findUserById(id);
		RequestUser dto = userToDtoUser(user);
		return dto;
	}

	@Override
	public List<RequestUser> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<RequestUser> dtoUsers = users.stream().map(dto -> userToDtoUser(dto)).collect(Collectors.toList());
		return dtoUsers;
	}

	@Override
	public Page<RequestUser> findAllPageableUsers(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		Page<RequestUser> dtoUsers = users.map(user -> userToDtoUser(user));
		return dtoUsers;
	}

	@Override
	public List<RequestUser> addFollower(User user) {
		User foundUser = findUserById(user.getId());
		User follower = findUserById(user.getUser().getId());
		RequestUser dto = userToDtoUser(follower);
		foundUser.getFollowers().add(dto);
		foundUser.setId(user.getId());
		userRepository.save(foundUser);
		return foundUser.getFollowers();
	}

	@Override
	public List<RequestUser> removeFollower(User user) {
		User foundUser = findUserById(user.getId());
		User follower = findUserById(user.getUser().getId());
		RequestUser dto = userToDtoUser(follower);
		foundUser.getFollowers().remove(dto);
		foundUser.setId(user.getId());
		userRepository.save(foundUser);
		return foundUser.getFollowers();
	}

	@Override
	public RequestUser addPhoto(User user) {
		User otherUser = findUserById(user.getId());
		otherUser.setId(user.getId());
		otherUser.setPhoto(user.getPhoto());
		RequestUser dto = userToDtoUser(userRepository.save(otherUser));
		return dto;
	}

}
