package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.LikeDAO;
import com.elevysi.site.blog.dao.LikeRepository;
import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.blog.entity.Like_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class LikeService extends BasicService{
	
	@Autowired
	private LikeDAO likeDAO;
	
	public Like saveLike(Like like){
		
		Date now = new Date();
		like.setCreated(now);
		like.setModified(now);
		
		return likeDAO.save(like);
	}

	public boolean isAlreadyLiked(Like like) {
		// TODO Auto-generated method stub
//		Like savedLike = likeRepository.findLikedItem(like.getItemId(), like.getItemType(), like.getLikeOwner().getId());
//		if(savedLike != null){
//			return true;
//		}
		
		return false;
	}
	
	public List<Like> itemLikes(Integer itemId, String itemType){
		com.elevysi.site.commons.pojo.Page page = buildOffsetPage(1, 200, Like_.created, com.elevysi.site.commons.pojo.Page.SortDirection.DESC);
		List<Like> itemLikes = likeDAO.itemLikes(page, itemId, itemType);
		
		return itemLikes;
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return likeDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
}
