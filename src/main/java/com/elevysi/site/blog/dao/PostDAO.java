package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

//@Transactional
public interface PostDAO extends AbstractDAO<Post, Integer>{
	
	public List<Post> searchFor(String term);
	public List<Post> searchByTerm(String term);
	public List<Post> getPosts(Page page);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public List<Post> getAllLatestForProfileExcept(ProfileDTO profile, Integer postId, Page page);
	public List<Post> getAllLatestForProfile(ProfileDTO profile, Page page);
	public Post findByID(Integer id);
}
