package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

//@Transactional
public interface PostDAO {
	
	public List<Post> findAllPosts();
	public Post loadPostForView(Integer postID);
	public List<Post> searchFor(String term);
	public List<Post> searchByTerm(String term);
	public Post getPost(int id);
	public List<Post> getPosts(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public void deletePost(int id);
	public List<Post> getAllLatestForProfileExcept(Profile profile, Integer postId, Page page);
	public List<Post> getAllLatestForProfile(Profile profile, Page page);
	
	
}
