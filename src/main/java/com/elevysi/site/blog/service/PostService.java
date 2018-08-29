package com.elevysi.site.blog.service;


import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.PostDAO;
import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Comment_;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;


@Service
public class PostService extends BasicService{
	
	@Autowired
	private PublicationService publicationService;
	
	private PostDAO postDAO;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	public PostService(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	
	public List<Post> findAllPosts() {
		return postDAO.findAll();
	}
	
	public Page<Post> findPaginatedAllPostsWithOwner(Integer pageNumber, Integer limit, String sortField, String sortDirection) {
//		return postRepository.findAllWithOwner(constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		return null;
	}
	
	public Post savePost(Post post){
		
		Date now = new Date();
		post.setCreated(now);
		post.setModified(now);
		
		Publication publication = post.getPublication();
		publication.setCreated(now);
		publication.setModified(now);
		publication.setPublicPublication(true);
		String slug = toSlug(post.getTitle());
		if(slug != null){
			publication.setFriendlyUrl(slug);
		}
		
		Post savedPost = postDAO.save(post);
		if(savedPost != null){
			publicationService.saveRelated(savedPost.getPublication().getId().intValue(), savedPost.getUuid());
		}
		
		return savedPost;
	}
	
	@PreAuthorize("#post.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
//	@PreAuthorize("#post.profile.id == authentication.principal.activeProfile.id") //Same as above
	public Post saveEditedPost(Post post){
		Date now = new Date();
		post.setModified(now);
		post.getPublication().setModified(now);
		
		String slug = toSlug(post.getTitle());
		if(slug != null){
			post.getPublication().setFriendlyUrl(slug);
		}
		
		if(post.getCreated() == null){
			post.setCreated(now);
		}
		
		Post savedPost = postDAO.save(post);
		if(savedPost != null){
			publicationService.saveRelated(savedPost.getPublication().getId().intValue(), savedPost.getUuid());
		}
		
		return savedPost;
	}
	

	public Post findByID(int id) {
		return postDAO.findByID(id);
	}
	
	public List<Post> findMatching(String term) {
		return postDAO.searchFor(term);
	}

	
	
	public List<Post> getLatestPostsForProfile(ProfileDTO profile, Post post, com.elevysi.site.commons.pojo.Page page){
		if(post != null){
			return postDAO.getAllLatestForProfileExcept(profile, post.getId(), page);
		}else{
			return postDAO.getAllLatestForProfile(profile, page);
		}
	}
	
	
	public void addComment(Post post, Comment comment){
		this.addComment(post, comment, true);
	}
	
	
	/**
	 * http://stackoverflow.com/questions/12909871/how-to-check-in-java-if-set-contains-object-with-some-string-value
	 * If your object's equals method is defined in terms of equality of that String property, and if the hashCode method is also implemented correctly, then you can use the hashSet.contains
	 * @param post
	 * @param comment
	 * @param set
	 */
	public void addComment(Post post, Comment comment, boolean set){
		if(comment != null){
			
			/**
			 * Using this approach because fecth type is lazy
			 * Eager would explode latency
			 */
			com.elevysi.site.commons.pojo.Page commentPage = commentService.buildOffsetPage(1, DEFAULT_NO_COMMENTS, Comment_.created , SortDirection.DESC);
			Set<Comment> postComments = new HashSet<Comment>(commentService.getCommentsForPublication(commentPage, post.getPublication().getId()));
			post.setComments(postComments);
			
			if(post.getComments().contains(comment)){
				post.getComments().remove(comment);
				post.getComments().add(comment);
				
				
			}else{
				post.getComments().add(comment);
			}
			
			if(set){
				commentService.setPost(comment, post, false);
			}
		}
	}

	
	public String truncate(String content, int lastIndex){
		String result = content.substring(0, lastIndex);
	    if (content.charAt(lastIndex) != ' ' && content.contains(" ")) {
	        result = result.substring(0, result.lastIndexOf(" "));
	    }
	    return result;
	}

	public Post updatePost(Post post) {
		return postDAO.save(post);
	}
	
	public List<Post> findAllPostsWithProfile(){
		return null;
	}
	
	public boolean canEditPost(ProfileDTO profileOwner, ProfileDTO profile){
		if(profileOwner != null && profile != null){
			if(profileOwner.equals(profile)){
				return true;
			}else if(profile.getProfile_type_id() == 1){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean canViewPost(){
		return true;
	}
	
	
//	public DataPage getAllPosts(int pageNo, int maxResults, String sortField, String sortDirection){
//		DataPage page = postDao.getPosts(this.getStart(pageNo, maxResults), maxResults, sortField, sortDirection);
//		return page;
//	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return postDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Post> getAllPosts(com.elevysi.site.commons.pojo.Page page){
		return postDAO.getPosts(page);
	}
	
	@PreAuthorize("#post.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void delete(Post post){
		postDAO.delete(post.getId());
	}

}
