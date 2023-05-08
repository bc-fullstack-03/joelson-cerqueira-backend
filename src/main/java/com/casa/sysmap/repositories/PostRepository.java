package com.casa.sysmap.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casa.sysmap.models.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, UUID> {

}
