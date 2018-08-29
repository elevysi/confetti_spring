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

import com.elevysi.site.blog.entity.Share;
import com.elevysi.site.blog.entity.Share_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class ShareDAOImplement extends AbstractDAOImpl<Share, Integer> implements ShareDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public long itemLikesCount(long itemID, String itemType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		criteria.select(cb.count(criteria.from(Share.class)));
		Root<Share> shareRoot = criteria.from(Share.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(shareRoot.get("itemId"), itemID));
		predicates.add(cb.equal(shareRoot.get("itemType"), "itemType"));
		
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return em.createQuery(criteria).getSingleResult();
	}
	
	public List<Share> itemShares(Page page, long itemID, String itemType){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Share> criteria = cb.createQuery(Share.class);
		Root<Share> shareRoot = criteria.from(Share.class);
		criteria.select(shareRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(shareRoot.get("itemId"), itemID));
		predicates.add(cb.equal(shareRoot.get("itemType"), "itemType"));
		criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Share> query = page.createQuery(em, criteria, shareRoot);
		List<Share> shares =  query.getResultList();
		return shares;
	}
	
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Share_.created, Share_.modified, Share_.id);
	}

}
