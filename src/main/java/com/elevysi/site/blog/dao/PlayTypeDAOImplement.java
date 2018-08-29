package com.elevysi.site.blog.dao;


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

import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.entity.PlayType_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class PlayTypeDAOImplement extends AbstractDAOImpl<PlayType, Integer> implements PlayTypeDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(PlayType.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	public PlayType findByID(long categoryID) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlayType> criteria = cb.createQuery(PlayType.class);
		Root<PlayType> playTypeRoot = criteria.from(PlayType.class);
		criteria.select(playTypeRoot);
		Predicate condition = cb.equal(playTypeRoot.get(PlayType_.id), categoryID);
		criteria.where(condition);
		TypedQuery<PlayType> query = em.createQuery(criteria);
		PlayType category = query.getSingleResult();
		
		return category;
	}
	
	public PlayType save(PlayType playType) {
		em.persist(playType);
		em.flush();
		return playType;
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, PlayType_.id);
	}
	

}
