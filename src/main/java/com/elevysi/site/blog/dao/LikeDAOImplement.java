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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.blog.entity.Like_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;


@Repository
@Transactional
public class LikeDAOImplement extends AbstractDAOImpl<Like, Integer> implements LikeDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public long itemLikesCount(long itemID, String itemType) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		criteria.select(cb.count(criteria.from(Like.class)));
		Root<Like> likeRoot = criteria.from(Like.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(likeRoot.get("itemId"), itemID));
		predicates.add(cb.equal(likeRoot.get("itemType"), "itemType"));
		
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return em.createQuery(criteria).getSingleResult();
	}
	
	public List<Like> itemLikes(Page page, long itemID, String itemType){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Like> criteria = cb.createQuery(Like.class);
		Root<Like> likeRoot = criteria.from(Like.class);
		criteria.select(likeRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(likeRoot.get("itemId"), itemID));
		predicates.add(cb.equal(likeRoot.get("itemType"), "itemType"));
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Like> query = page.createQuery(em, criteria, likeRoot);
		List<Like> likes =  query.getResultList();
		return likes;
	}

	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Like_.created, Like_.modified, Like_.id);
	}
}
