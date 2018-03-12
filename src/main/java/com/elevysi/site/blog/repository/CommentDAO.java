package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface CommentDAO {

	public Comment searchFor(String term);
	public List<Comment> getComments(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
}
