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

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Category_;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class CategoryDAOImplement extends AbstractDAOImpl<Category, Integer> implements CategoryDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Category_.id);
	}
	
	public Category findByName(String name){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = cb.createQuery(Category.class);
		Root<Category> categoryRoot = criteria.from(Category.class);
		criteria.select(categoryRoot);
		Predicate condition = cb.equal(categoryRoot.get(Category_.name), name);
		criteria.where(condition);
		TypedQuery<Category> query = em.createQuery(criteria);
		
		List<Category> results = query.getResultList();
		if(!results.isEmpty()){
			Category category = results.get(0);
			Hibernate.initialize(category.getPublications());
			return category;
		}
		
		return null;
	}
	
}
