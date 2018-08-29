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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Play_;

@Repository
@Transactional
public class PlayDAOImplement extends AbstractDAOImpl<Play, Integer> implements PlayDAO{
	
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
//			play.setPlayProfile(getProfile(play.getProfileID()));
			Hibernate.initialize(play.getPublication());
		}
		
		return plays;
		
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
	
	@Override
	public Play findByID(Integer id){
		Play play = em.find(Play.class, id);
		if(play != null) {
			Hibernate.initialize(play.getPublication());
			Hibernate.initialize(play.getPublication().getDossier());
			Hibernate.initialize(play.getPublication().getCategories());
			ProfileDTO profile = getProfile(play.getProfileID());
			play.getPublication().setProfile(profile);
			play.setPlayProfile(profile);
		}
		
		return play;
	}
	
	public List<Play> getAllLatestForProfile(ProfileDTO profile, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Play> criteria = cb.createQuery(Play.class);
		Root<Play> root = criteria.from(Play.class);
		criteria.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get(Play_.profileID), profile.getId()));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Play> query = page.createQuery(em, criteria, root);
		List<Play> plays =  query.getResultList();
		
		for(Play play : plays){
			Hibernate.initialize(play.getPlayProfile());
			Hibernate.initialize(play.getPublication());
			Hibernate.initialize(play.getPublication().getCategories());
		}
		
		return plays;
	}
	
	

}
