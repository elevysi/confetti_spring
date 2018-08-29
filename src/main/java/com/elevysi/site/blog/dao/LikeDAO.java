package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface LikeDAO extends AbstractDAO<Like, Integer>{
	
	public long itemLikesCount(long itemID, String itemType);
	public List<Like> itemLikes(Page page, long itemID, String itemType);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);

}
