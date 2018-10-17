package com.elevysi.site.blog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Post_;

@Repository
@Transactional
public class PostDAOImplement extends AbstractDAOImpl<Post, Integer> implements PostDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Post> searchFor(String term){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		TypedQuery<Post> query = em.createQuery("SELECT post FROM Post post WHERE "+
	            "LOWER(post.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(post.content) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(post.description) LIKE LOWER(CONCAT('%',:searchTerm, '%')) ",
						Post.class
		).setParameter("searchTerm", term);
		
		List<Post> posts = query.getResultList();
		
		for(Post post : posts){
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getPublication().getUploads());
		}
		
		return posts;
	}
	
	public List<Post> searchByTerm(String term){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.like(postRoot.get(Post_.title), "%"+term+"%"));
		predicates.add(cb.like(postRoot.get(Post_.description), "%"+term+"%"));
		predicates.add(cb.like(postRoot.get(Post_.content), "%"+term+"%"));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Post> query = em.createQuery(criteria);
		List<Post> posts =  query.getResultList();
		
		for(Post post: posts){
			Hibernate.initialize(post.getPublication());
		}
		
		return posts;
	}
	
	
	public List<Post> getPosts(Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> post = criteria.from(Post.class);
		criteria.select(post);
		
		TypedQuery<Post> query = page.createQuery(em, criteria, post);
		List<Post> posts =  query.getResultList();
		
		for(Post postItem : posts){
			Hibernate.initialize(postItem.getPublication());
			Hibernate.initialize(postItem.getPublication().getCategories());
			Hibernate.initialize(postItem.getPublication().getUploads());
		}
		
		return posts;
		
	}
	
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Post.class)));
		
		return em.createQuery(cq).getSingleResult();
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Post_.created, Post_.modified, Post_.id, Post_.title, Post_.description);
	}
	
	@Override
	public Post findByID(Integer id){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		Post post = em.find(Post.class, id);
		
		if(post != null) {
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getPublication().getCategories());
			Hibernate.initialize(post.getPublication().getDossiers());
			Hibernate.initialize(post.getPublication().getUploads());
			
			/**
			 * Look for the post profile information
			 */
			
			ProfileDTO profile = getProfile(post.getProfileID());
			post.setProfile(profile);
			post.getPublication().setProfile(profile);
			
		}
		
		return post;
	}
	
	public List<Post> getAllLatestForProfileExcept(ProfileDTO profile, Integer postId, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.notEqual(postRoot.get(Post_.id), postId));
		predicates.add(cb.equal(postRoot.get(Post_.profileID), profile.getId()));
		
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Post> query = page.createQuery(em, criteria, postRoot);
		List<Post> posts =  query.getResultList();
		
		for(Post post: posts){
			Hibernate.initialize(post.getProfile());
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getPublication().getCategories());
			Hibernate.initialize(post.getPublication().getUploads());
		}
		
		return posts;
	}
	
	public List<Post> getAllLatestForProfile(ProfileDTO profile, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(postRoot.get(Post_.profileID), profile.getId()));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Post> query = page.createQuery(em, criteria, postRoot);
		List<Post> posts =  query.getResultList();
		
		for(Post post: posts){
			Hibernate.initialize(post.getProfile());
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getPublication().getCategories());
			Hibernate.initialize(post.getPublication().getUploads());
		}
		
		return posts;
	}
}
