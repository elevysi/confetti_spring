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

import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Comment_;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Post_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class CommentDAOImplement implements CommentDAO{

	@PersistenceContext
	private EntityManager em;

	public Comment searchFor(String term){
		return null;
	}
	
	public List<Comment> getComments(Page page){
//		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("commentItemIs");
//		filter.setParameter("item_typeValue", "posts");
		
		em.unwrap(Session.class).enableFilter("publicationComment");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);
		Root<Comment> comment = criteria.from(Comment.class);
		criteria.select(comment);
		
		TypedQuery<Comment> query = page.createQuery(em, criteria, comment);
		
		List<Comment> comments = query.getResultList();
		for(Comment commentItem : comments){
			Hibernate.initialize(commentItem.getPublication());
//			Hibernate.initialize(commentItem.getPublication().getPost());
//			Hibernate.initialize(commentItem.getPublication().getPlay());
		}
		return comments;
	}
	
	
	public List<Comment> getCommentsForItem(Page page){
		
		em.unwrap(Session.class).enableFilter("postComment");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);
		Root<Comment> comment = criteria.from(Comment.class);
		criteria.select(comment);
		
			
		
		TypedQuery<Comment> query = page.createQuery(em, criteria, comment);
		
		List<Comment> comments = query.getResultList();
		for(Comment commentItem : comments){
			Hibernate.initialize(commentItem.getPost());
//			Hibernate.initialize(commentItem.getPlay());
			Hibernate.initialize(commentItem.getProfile());
		}
		return comments;
	}
	
	public List<Comment> getCommentsForPublication(Page page, long publicationID){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);
		Root<Comment> commentRoot = criteria.from(Comment.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(commentRoot.get(Comment_.itemId), publicationID));
		predicates.add(cb.equal(commentRoot.get(Comment_.itemType), "publications"));
		
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		TypedQuery<Comment> query = page.createQuery(em, criteria, commentRoot);
		List<Comment> comments = query.getResultList();
		
		return comments;
	}
	
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Comment.class)));
		
		
		return em.createQuery(cq).getSingleResult();
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Comment_.created, Comment_.modified, Comment_.id, Comment_.message);
	}
	
	public Comment findByID(long commentID) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);
		Root<Comment> commentRoot = criteria.from(Comment.class);
		criteria.select(commentRoot);
		Predicate condition = cb.equal(commentRoot.get(Comment_.id), commentID);
		criteria.where(condition);
		TypedQuery<Comment> query = em.createQuery(criteria);
		Comment comment = query.getSingleResult();
		
		return comment;
	}
	
	public Comment save(Comment comment) {
		em.persist(comment);
		em.flush();
		return comment;
	}
}
