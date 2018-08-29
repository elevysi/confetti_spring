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

import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Dossier_;

@Repository
@Transactional
public class DossierDAOImplement extends AbstractDAOImpl<Dossier, Integer> implements DossierDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Dossier findByID(Integer id){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		Dossier dossier = em.find(Dossier.class, id);
		if(dossier != null) {
			Hibernate.initialize(dossier.getPublications());
			Hibernate.initialize(dossier.getPublication());
			Hibernate.initialize(dossier.getPublication().getCategories());
			Hibernate.initialize(dossier.getPublication().getUploads());
			dossier.getPublication().setProfile(getProfile(dossier.getPublication().getProfileID()));
		}
		
		return dossier;
	}
	
	public List<Dossier> getDossiers(){
		return null;
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Dossier_.created, Dossier_.modified, Dossier_.id, Dossier_.name);
	}
	
	public List<Dossier> getDossiers(Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		
		TypedQuery<Dossier> query = page.createQuery(em, criteria, dossierRoot);
		List<Dossier> dossiers =  query.getResultList();
		for(Dossier dossier : dossiers){
			
			Hibernate.initialize(dossier.getPublications());
			Hibernate.initialize(dossier.getPublication().getUploads());
		}
		return dossiers;
		
	}
	
	public List<Dossier> getDossiersForProfile(ProfileDTO profile, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		
		Predicate condition = cb.equal(dossierRoot.get(Dossier_.profileID), profile.getId());
		criteria.where(condition);
		
		TypedQuery<Dossier> query = page.createQuery(em, criteria, dossierRoot);
		List<Dossier> dossiers =  query.getResultList();
		for(Dossier dossier : dossiers){
			Hibernate.initialize(dossier.getPublication());
			Hibernate.initialize(dossier.getPublications());
			Hibernate.initialize(dossier.getPublication().getUploads());
		}
		return dossiers;
	}
	
	public List<Dossier> searchByTerm(String term){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.like(dossierRoot.get(Dossier_.name), "%"+term+"%"));
		predicates.add(cb.like(dossierRoot.get(Dossier_.description), "%"+term+"%"));
		
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Dossier> query = em.createQuery(criteria);
		List<Dossier> dossiers =  query.getResultList();
		
		for(Dossier dossier : dossiers){
			Hibernate.initialize(dossier.getPublications());
		}
		
		return dossiers;
	}
	
	public List<Dossier> findAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		TypedQuery<Dossier> query = em.createQuery(criteria);
		List<Dossier> dossiers = query.getResultList();
		return dossiers;
	}

}
