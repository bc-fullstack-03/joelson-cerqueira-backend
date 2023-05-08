package com.casa.sysmap.services;

import java.util.UUID;

import com.casa.sysmap.models.Like;

public interface LikeService {

	Like createLike(Like like);

	void deleteLikeById(UUID id);

	Like findLikeById(UUID id);

}
