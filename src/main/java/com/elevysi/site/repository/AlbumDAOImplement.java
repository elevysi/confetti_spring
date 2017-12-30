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
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Album_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class AlbumDAOImplement implements AlbumDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Album> searchFor(String term){
		return null;
	}
	
	public List<Album> getAlbums(Page page){
		em.unwrap(Session.class).enableFilter("profileAlbum");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
		Root<Album> albumRoot = criteria.from(Album.class);
		criteria.select(albumRoot);
		
		TypedQuery<Album> query = page.createQuery(em, criteria, albumRoot);
		List<Album> albums =  query.getResultList();
		
		for(Album album : albums){
			Hibernate.initialize(album.getProfileOwner());
			Hibernate.initialize(album.getUploads());
		}
		
		return albums;
	}
	public long getCount(){
		em.unwrap(Session.class).enableFilter("profileAlbum");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Album.class)));
		
		return em.createQuery(cq).getSingleResult();
	}
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Album_.created, Album_.modified, Album_.id, Album_.name, Album_.description, Album_.place);
		
	}
	
	public Album findById(int id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
		Root<Album> queryRoot = criteria.from(Album.class);
		Predicate condition = cb.equal(queryRoot.get(Album_.id), id);
		criteria.where(condition);
		TypedQuery<Album> query = em.createQuery(criteria);
		Album album = query.getSingleResult();
		
		Hibernate.initialize(album.getUploads());
		Hibernate.initialize(album.getDossier());
		Hibernate.initialize(album.getPublication());
		Hibernate.initialize(album.getProfileOwner());
		
		
		return album;
	}
	
	public List<Album> searchByTerm(String term){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
		Root<Album> albumRoot = criteria.from(Album.class);
		criteria.select(albumRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.like(albumRoot.get(Album_.name), "%"+term+"%"));
		predicates.add(cb.like(albumRoot.get(Album_.description), "%"+term+"%"));
		predicates.add(cb.like(albumRoot.get(Album_.place), "%"+term+"%"));
		
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Album> query = em.createQuery(criteria);
		List<Album> albums =  query.getResultList();
		
		for(Album album : albums){
			Hibernate.initialize(album.getPublication());
		}
		
		return albums;
	}
	
	public void deleteAlbum(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
		Root<Album> albumRoot = criteria.from(Album.class);
		criteria.select(albumRoot);
		Predicate condition = cb.equal(albumRoot.get(Album_.id), id);
		criteria.where(condition);
		TypedQuery<Album> query = em.createQuery(criteria);
		Album album = query.getSingleResult();
		
		em.merge(album);
		em.remove(album);
	}
	
	public List<Album> getAlbumsForProfile(Profile profile, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
		Root<Album> queryRoot = criteria.from(Album.class);
		Predicate condition = cb.equal(queryRoot.get(Album_.profileOwner), profile);
		criteria.where(condition);
		TypedQuery<Album> query = em.createQuery(criteria);
		List<Album> albums = query.getResultList();
		
		for(Album album : albums){
			Hibernate.initialize(album.getUploads());
			Hibernate.initialize(album.getDossier());
			Hibernate.initialize(album.getPublication());
			Hibernate.initialize(album.getProfileOwner());
		}
		
		return albums;
	}

}
