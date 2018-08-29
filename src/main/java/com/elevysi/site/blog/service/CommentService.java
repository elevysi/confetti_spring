package com.elevysi.site.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.CommentDAO;
import com.elevysi.site.blog.dao.CommentRepository;
import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class CommentService extends BasicService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostService postService;
	
	
	@Autowired
	private CommentDAO commentDAO;
	
	public void setPost(Comment comment, Post post, boolean add) {
		comment.setPost(post);
		
		if(post!= null && add){
			postService.addComment(post, comment, false);
		}
	}
	
	public void setProfile(Comment comment, ProfileDTO profile, boolean add) {
//		comment.setProfile(profile);
//		
//		if(profile!= null && add){
//			profileService.addComment(profile, comment, false);
//		}
	}

	public Comment save(Comment comment) {
		
		Date now = new Date();
		comment.setCreated(now);
		comment.setModified(now);
		return commentRepository.save(comment);
	}
	
	public Comment findByID(long commentID) {
		 return commentDAO.findByID(commentID);
	}
	
	
	
	public List<Comment> getAllComments(com.elevysi.site.commons.pojo.Page page){
		return commentDAO.getComments(page);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return commentDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Comment> getCommentsForPublication(com.elevysi.site.commons.pojo.Page page, long publicationID){
		return commentDAO.getCommentsForPublication(page, publicationID);
	}
	
	

}
