package com.elevysi.site.blog.dao;

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

import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.PublicationStatus_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class PublicationStatusDAOImplement extends AbstractDAOImpl<PublicationStatus, Integer> implements PublicationStatusDAO{

	@PersistenceContext
	private EntityManager em;
	
//	public PublicationStatusDAOImplement(Class<PublicationStatus> type){
//		super(type);
//	}
	
	public PublicationStatus save(PublicationStatus postStatus) {
		em.persist(postStatus);
		em.flush();
		return postStatus;
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, PublicationStatus_.id);
	}
	
}
