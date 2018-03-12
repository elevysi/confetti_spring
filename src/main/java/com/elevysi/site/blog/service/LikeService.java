package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.blog.repository.LikeRepository;

@Service
public class LikeService extends AbstractService{
	
	@Autowired
	private LikeRepository likeRepository;

//	public Like saveLike(Integer itemId, String itemType) {
//		
//		Like like = new Like(itemId, itemType);
//		Like savedLiked = likeRepository.save(like);
//		return savedLiked;
//	}
	
	public Like saveLike(Like like){
		
		Date now = new Date();
		like.setCreated(now);
		like.setModified(now);
		
		return likeRepository.save(like);
	}

	public boolean isAlreadyLiked(Like like) {
		// TODO Auto-generated method stub
		Like savedLike = likeRepository.findLikedItem(like.getItemId(), like.getItemType(), like.getLikeOwner().getId());
		if(savedLike != null){
			return true;
		}
		
		return false;
	}
	
	public Set<Like> itemLikes(Integer itemId, String itemType){
		Set<Like> itemLikes = likeRepository.findByItemIdAndItemType(itemId, itemType);
		
		return itemLikes;
	}
	
	public Set<Like> itemFullLikes(Integer itemId, String itemType){
		Set<Like> itemLikes = likeRepository.findItemLikes(itemId, itemType);
		
		return itemLikes;
		
	}
}
