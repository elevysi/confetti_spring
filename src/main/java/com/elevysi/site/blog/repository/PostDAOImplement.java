package com.elevysi.site.blog.repository;

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
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Post_;

@Repository
@Transactional
public class PostDAOImplement extends AbstractDAO implements PostDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Post> findAllPosts(){ return null;
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
//		
//		return em.createQuery(criteria).getResultList();
		
	}
	//Hibernate pp 346 CReate query
	public Post loadPostForView(Integer postID){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		TypedQuery<Post> query = em.createQuery("SELECT post FROM Post post "
						+ " LEFT JOIN FETCH post.categories postCategories "
						+ " LEFT JOIN FETCH post.publication publication "
						+ " LEFT JOIN FETCH post.dossier dossier "
						+ " LEFT JOIN FETCH post.profile profile "
						+ " LEFT JOIN FETCH post.uploads uploads "
						+ " WHERE post.id = :id ",
						Post.class
		).setParameter("id", postID);
		
		Post post = query.getSingleResult();
		
//		String sqlString = "SELECT * from posts where Post.id=:postID";
//		
//		Query sqlQuery = em.createNativeQuery(sqlString, Post.class);
//		sqlQuery.setParameter("postID", postID);
//		Post post = (Post)sqlQuery.getSingleResult();
//		
//		Hibernate.initialize(post.getCategories());
//		Hibernate.initialize(post.getUploads());
//		Hibernate.initialize(post.getProfile());
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
//		
//		return em.createQuery(criteria).getResultList();
//		
//		
		
//		
		return post;
	}
	
	public List<Post> searchFor(String term){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		TypedQuery<Post> query = em.createQuery("SELECT post FROM Post post WHERE "+
	            "LOWER(post.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(post.content) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(post.description) LIKE LOWER(CONCAT('%',:searchTerm, '%')) ",
						Post.class
		).setParameter("searchTerm", term);
		
		List<Post> posts = query.getResultList();
		
		for(Post post : posts){
			Hibernate.initialize(post.getUploads());
			Hibernate.initialize(post.getProfile());
			Hibernate.initialize(post.getPublication());
		}
		
		return posts;
	}
	
	public List<Post> searchByTerm(String term){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
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
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> post = criteria.from(Post.class);
		criteria.select(post);
		
		TypedQuery<Post> query = page.createQuery(em, criteria, post);
		List<Post> posts =  query.getResultList();
		
		for(Post postItem : posts){
			Hibernate.initialize(postItem.getUploads());
			Hibernate.initialize(postItem.getProfile());
			Hibernate.initialize(postItem.getCategories());
			Hibernate.initialize(postItem.getPublication());
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
	
	public Post getPost(int id){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		Predicate condition = cb.equal(postRoot.get(Post_.id), id);
		criteria.where(condition);
		TypedQuery<Post> query = em.createQuery(criteria);
		Post post = query.getSingleResult();
		
		Hibernate.initialize(post.getCategories());
		Hibernate.initialize(post.getPublication());
		Hibernate.initialize(post.getDossier());
		Hibernate.initialize(post.getUploads());
		Hibernate.initialize(post.getProfile());
		Hibernate.initialize(post.getProfile().getProfilePicture());
		
		
		return post;
	}
	
	public void deletePost(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		Predicate condition = cb.equal(postRoot.get(Post_.id), id);
		criteria.where(condition);
		TypedQuery<Post> query = em.createQuery(criteria);
		Post post = query.getSingleResult();
		
		em.merge(post);
		em.remove(post);
		
	}
	
	public List<Post> getAllLatestForProfileExcept(Profile profile, Integer postId, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.notEqual(postRoot.get(Post_.id), postId));
		predicates.add(cb.notEqual(postRoot.get(Post_.profile), profile));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Post> query = page.createQuery(em, criteria, postRoot);
		List<Post> posts =  query.getResultList();
		
		for(Post post: posts){
			Hibernate.initialize(post.getProfile());
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getCategories());
			Hibernate.initialize(post.getUploads());
		}
		
		return posts;
	}
	
	public List<Post> getAllLatestForProfile(Profile profile, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "posts");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = cb.createQuery(Post.class);
		Root<Post> postRoot = criteria.from(Post.class);
		criteria.select(postRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(postRoot.get(Post_.profile), profile));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Post> query = page.createQuery(em, criteria, postRoot);
		List<Post> posts =  query.getResultList();
		
		for(Post post: posts){
			Hibernate.initialize(post.getProfile());
			Hibernate.initialize(post.getPublication());
			Hibernate.initialize(post.getCategories());
			Hibernate.initialize(post.getUploads());
		}
		
		return posts;
	}
	
}
