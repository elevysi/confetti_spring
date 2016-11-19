package com.elevysi.site.repository;

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

import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Dossier_;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class DossierDAOImplement implements DossierDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public Dossier getDossier(int id){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "dossiers");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> from = criteria.from(Dossier.class);
		
		Predicate condition = cb.equal(from.get(Dossier_.id), id);
		criteria.where(condition);
		TypedQuery<Dossier> query = em.createQuery(criteria);
		Dossier dossier = query.getSingleResult();
		
		Hibernate.initialize(dossier.getProfile());
		Hibernate.initialize(dossier.getProfile().getProfilePicture());
		Hibernate.initialize(dossier.getPosts());
		Hibernate.initialize(dossier.getPlays());
		Hibernate.initialize(dossier.getAlbums());
		Hibernate.initialize(dossier.getUploads());
		
		return dossier;
	}
	public Dossier saveDossier(Dossier dossier){
		em.persist(dossier);
		em.flush();
		return dossier;
		
	}
	
	public Dossier saveEditedDossier(Dossier dossier){
		em.merge(dossier);
		em.flush();
		return dossier;
		
	}
	public List<Dossier> getDossiers(){
		return null;
	}
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Dossier.class)));
		
		return em.createQuery(cq).getSingleResult();
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Dossier_.created, Dossier_.modified, Dossier_.id, Dossier_.name);
	}
	
	public List<Dossier> getDossiers(Page page){
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "dossiers");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		
		TypedQuery<Dossier> query = page.createQuery(em, criteria, dossierRoot);
		List<Dossier> dossiers =  query.getResultList();
		for(Dossier dossier : dossiers){
			Hibernate.initialize(dossier.getProfile());
			Hibernate.initialize(dossier.getProfile().getProfilePicture());
			Hibernate.initialize(dossier.getPosts());
			Hibernate.initialize(dossier.getPlays());
			Hibernate.initialize(dossier.getAlbums());
			Hibernate.initialize(dossier.getUploads());
		}
		return dossiers;
		
	}
	
	public void deleteDossier(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dossier> criteria = cb.createQuery(Dossier.class);
		Root<Dossier> dossierRoot = criteria.from(Dossier.class);
		criteria.select(dossierRoot);
		Predicate condition = cb.equal(dossierRoot.get(Dossier_.id), id);
		criteria.where(condition);
		TypedQuery<Dossier> query = em.createQuery(criteria);
		Dossier dossier = query.getSingleResult();
		
		em.merge(dossier);
		em.remove(dossier);
	}

}
