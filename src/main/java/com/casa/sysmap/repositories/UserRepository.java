package com.casa.sysmap.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casa.sysmap.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

	Optional<User> findUserByEmail(String email);

}
