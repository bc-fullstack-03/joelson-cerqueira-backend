package com.casa.sysmap.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.sysmap.exceptions.ObjectNotFoundException;
import com.casa.sysmap.models.Like;
import com.casa.sysmap.repositories.LikeRepository;
import com.casa.sysmap.services.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository likeRepository;

	@Override
	public Like createLike(Like like) {
		return likeRepository.save(like);
	}

	@Override
	public void deleteLikeById(UUID id) {
		Like like = findLikeById(id);
		likeRepository.delete(like);
	}

	@Override
	public Like findLikeById(UUID id) {
		Like like = likeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Like not found"));
		return like;
	}

}
