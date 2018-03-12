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

import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.User;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.repository.CommentDAO;
import com.elevysi.site.blog.repository.CommentRepository;

@Service
public class CommentService extends AbstractService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private CommentDAO commentDAO;
	
	
	

	public Set<Comment> findPostComment(Post post) {
		
		if(post != null){
			return commentRepository.findByPost(post);
		}
		
		return null;
	}
	
	
	public Page<Comment> findPaginatedPostComments(Integer postId, Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		
		/**
		 * Modify pageNumber to use 1 index instead of zero index pages
		 */
		
		Page<Comment> requestedPage = commentRepository.findPostComment(postId, constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));
		return requestedPage;
		
	}
	
	
	public Page<Comment> findPaginatedPlayComments(Integer playId, Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		
		/**
		 * Modify pageNumber to use 1 index instead of zero index pages
		 */
		
		Page<Comment> requestedPage = commentRepository.findPlayComments(playId, constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));
		return requestedPage;
		
	}
	
	
	public Set<Comment> findByPostComment(Post post) {
	
		Set<Comment> comments = new HashSet<Comment>();
			
		if(post != null){
			Set<Comment> foundComments = commentRepository.findByPost(post);
			
			if(foundComments != null) return foundComments;
		}
		
		return comments;
	}
	
	
	public void setPost(Comment comment, Post post, boolean add) {
		comment.setPost(post);
		
		if(post!= null && add){
			postService.addComment(post, comment, false);
		}
	}
	
	public void setProfile(Comment comment, Profile profile, boolean add) {
		comment.setProfile(profile);
		
		if(profile!= null && add){
			profileService.addComment(profile, comment, false);
		}
	}

	public Comment save(Comment comment) {
		
		Date now = new Date();
		comment.setCreated(now);
		comment.setModified(now);
		return commentRepository.save(comment);
	}


	public Set<Comment> findByProfileComment(Profile profile) {
		Set<Comment> comments = new HashSet<Comment>();
		
		if(profile != null){
			Set<Comment> foundComments = commentRepository.findByProfile(profile);
			
			if(foundComments != null) return foundComments;
		}
		
		return comments;
	}
	
	
	public List<Comment> findLatestComments(Integer pageNumber, Integer limit, String sortField, String sortDirection){
		
		return findPaginatedComments(pageNumber, limit, sortField, sortDirection).getContent();
		
	}
	
	public Page<Comment> findPaginatedComments(Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		
		/**
		 * Modify pageNumber to use 1 index instead of zero index pages
		 */
		
		Page<Comment> requestedPage = commentRepository.findComment(constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));
		return requestedPage;
		
	}
	
	public Comment findOne(Integer id){
		return commentRepository.findById(id);
	}
	
	public Set<Comment> itemComments(Integer itemId, String itemType){
		
		Set<Comment> comments = commentRepository.findByItemIdAndItemType(itemId, itemType);
				
		return comments;
	}
	
	public int itemCommentsCount(Integer itemId, String itemType){
		return this.itemComments(itemId, itemType).size();
	}
	
	public List<Comment> getAllComments(com.elevysi.site.blog.pojo.Page page){
		return commentDAO.getComments(page);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return commentDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	

}
