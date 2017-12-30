package com.elevysi.site.repository;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Play_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class PlayDAOImplement implements PlayDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Play> searchFor(String term){
		return null;
	}
	
	public List<Play> getPlays(Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> playRoot = criteria.from(Play.class);
		criteria.select(playRoot);
		
		TypedQuery<Play> query = page.createQuery(em, criteria, playRoot);
		List<Play> plays =  query.getResultList();
		
		for(Play play : plays){
			Hibernate.initialize(play.getPlayProfile());
			Hibernate.initialize(play.getPublication());
		}
		
		return plays;
		
	}
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Play.class)));
		
		return em.createQuery(cq).getSingleResult();
	}
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Play_.created, Play_.modified, Play_.id, Play_.title, Play_.description);
	}

	public List<Play> searchByTerm(String term){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> playRoot = criteria.from(Play.class);
		criteria.select(playRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.like(playRoot.get(Play_.title), "%"+term+"%"));
		predicates.add(cb.like(playRoot.get(Play_.description), "%"+term+"%"));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Play> query = em.createQuery(criteria);
		List<Play> plays =  query.getResultList();
		
		for(Play play : plays){
			Hibernate.initialize(play.getPublication());
		}
		
		return plays;
	}
	
	public Play getPlay(int id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> playRoot = criteria.from(Play.class);
		criteria.select(playRoot);
		Predicate condition = cb.equal(playRoot.get(Play_.id), id);
		criteria.where(condition);
		TypedQuery<Play> query = em.createQuery(criteria);
		Play play = query.getSingleResult();
		
		Hibernate.initialize(play.getPlayProfile());
		Hibernate.initialize(play.getPublication());
		Hibernate.initialize(play.getPlayProfile().getProfilePicture());
		Hibernate.initialize(play.getDossier());
		
		return play;
	}
	
	public void deletePlay(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> playRoot = criteria.from(Play.class);
		criteria.select(playRoot);
		Predicate condition = cb.equal(playRoot.get(Play_.id), id);
		criteria.where(condition);
		TypedQuery<Play> query = em.createQuery(criteria);
		Play play = query.getSingleResult();
		
		em.merge(play);
		em.remove(play);
		
	}
	
	public List<Play> getAllLatestForProfile(Profile profile, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> root = criteria.from(Play.class);
		criteria.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get(Play_.playProfile), profile));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Play> query = page.createQuery(em, criteria, root);
		List<Play> plays =  query.getResultList();
		
		for(Play play : plays){
			Hibernate.initialize(play.getPlayProfile());
			Hibernate.initialize(play.getPublication());
		}
		
		return plays;
	}

}
