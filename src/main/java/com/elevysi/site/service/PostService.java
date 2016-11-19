package com.elevysi.site.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Category;
import com.elevysi.site.entity.Comment;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Post_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.User;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.repository.AlbumRepository;
import com.elevysi.site.repository.PostDAO;
import com.elevysi.site.repository.PostRepository;
import com.elevysi.site.repository.PublicationRepository;


@Service
public class PostService extends AbstractService{
		
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PostDAO postDao;
	
	
	public List<Post> findAllPosts() {
		
		return postRepository.findAll();
	}
	
	public List<Post> findAllPosts2() {
		
		return postDao.findAllPosts();
	}
	
	public Page<Post> findPaginatedAllPostsWithOwner(Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		return postRepository.findAllWithOwner(constructPageSpecification(pageNumber, limit, sortField, sortDirection));
	}
	
	public Post savePost(Post post){
		
		Date now = new Date();
		post.setCreated(now);
		post.setModified(now);
		
		Publication publication = savePublication(post.getProfile());
		if(publication != null){
			post.setPublication(publication);
		}
		Post savedPost = postRepository.save(post);
		saveRelated(savedPost);
		
		return savedPost;
	}
	
	@PreAuthorize("#post.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
//	@PreAuthorize("#post.profile.id == authentication.principal.activeProfile.id") //Same as above
	public Post saveEditedPost(Post post){
		Date now = new Date();
		post.setModified(now);
		
		/**
		 * Retrieve all the uploads related to this post
		 */
		Post savedPost = postRepository.save(post);
		saveRelated(savedPost);
		
		return savedPost;
	}
	
	public void saveRelated(Post savedPost){
		String uuid = savedPost.getUuid();
		if(savedPost != null){
			if(uuid != null){
				List<Upload> relatedUploads = uploadService.findByUuidAndDisplay(uuid, true);
				
				if(relatedUploads != null){
					for (Upload upload : relatedUploads) {
						uploadService.addItemTable(upload, savedPost.getId(), "posts");
					}
				}				
			}
		}
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

	
	public List<Post> findMatching(String term) {
		return postDao.searchFor(term);
	}

	public List<Post> findLatestPosts(Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		
		Page<Post> requestedPage = postRepository.findAllLatest(constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		return requestedPage.getContent();
	}
	
	public List<Post> findLatestPostsForProfile(Profile profile, Post post, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		Page<Post> requestedPage;
		Integer viewedPostId = post.getId();
		if(viewedPostId != null){
			requestedPage = postRepository.findAllLatestForProfileExcept(profile.getId(), viewedPostId, constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		}else{
			requestedPage = postRepository.findAllLatestForProfile(profile.getId(), constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		}
		
		return requestedPage.getContent();
	}
	
	

	public List<Post> findByProfile(Profile profile) {
		
		return postRepository.findByProfile(profile);
	}
	
	public List<Post> findProfilePosts(Profile profile, Integer pageNubmer, Integer limit, String sortField, String sortDirection) {
		
		return postRepository.findProfilePosts(profile.getId(), constructPageSpecification(pageNubmer - 1, limit, sortField, sortDirection)).getContent();
	}
	
	
	public void addComment(Post post, Comment comment){
		this.addComment(post, comment, true);
	}
	
	@Autowired
	private CommentService commentService;
	
	
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
			Set<Comment> postComments = commentService.findByPostComment(post);
			
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
	
	public Post loadFullPost(Integer id){
		
		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Post> cq = cb.createQuery(Post.class);		
//		
//		ListJoin<Post, Category> postJoins = cq.from(Post.class).join(Post_.categories);
//		
//		
//		
//		Root<Post> post = cq.from(Post.class);
//		cq.multiselect(postJoins).where( cb.equal(post.get(Post_.id), id )).distinct(true);
//		
//		
//		TypedQuery<Post> typedQuery = entityManager.createQuery(cq);
//		Post resultPost = typedQuery.getSingleResult();
		
//		resultPost
//		Root<Category> from = criteriaQuery.from(Category.class);
//		Path<Object> path = from.join("product").get("category");
//		 
//		CriteriaQuery<Object> select = criteriaQuery.select(from);
//		select.where(criteriaBuilder.equal(path, category));
//		 
//		TypedQuery<Object> typedQuery = entityManager.createQuery(select);
		
//		return resultPost;
		
		
		return postDao.loadPostForView(id);
		
	}

	
	
	public String truncate(String content, int lastIndex){
		String result = content.substring(0, lastIndex);
	    if (content.charAt(lastIndex) != ' ') {
	        result = result.substring(0, result.lastIndexOf(" "));
	    }
	    return result;
	}

	

	public Post updatePost(Post post) {
		return postRepository.save(post);
	}
	
	public List<Post> findAllPostsWithProfile(){
		return null;
	}
	
	public boolean canEditPost(Profile profileOwner, Profile profile){
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
		return postDao.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Post> getAllPosts(com.elevysi.site.pojo.Page page){
		return postDao.getPosts(page);
	}
	
	public Post getPost(int id){
		return postDao.getPost(id);
	}
	
	@PreAuthorize("#post.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void deletePost(Post post){
		postDao.deletePost(post.getId());
	}

}
