package com.casa.sysmap.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.casa.sysmap.models.User;
import com.casa.sysmap.models.dto.RequestUser;

public interface UserService {

	RequestUser createUser(User user);

	RequestUser updateUser(UUID id, User user);

	void deleteUserById(UUID id);

	User findUserById(UUID id);
	
	User findUserByEmail(String email);
	
	User getUser(String email);

	RequestUser findDtoUserById(UUID id);

	List<RequestUser> findAllUsers();

	Page<RequestUser> findAllPageableUsers(Pageable pageable);

	List<RequestUser> addFriend(User user);

	List<RequestUser> removeFriend(User user);

	RequestUser addPhoto(User user);
}
