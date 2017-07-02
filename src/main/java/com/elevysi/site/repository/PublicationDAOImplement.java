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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Profile_;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Publication_;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class PublicationDAOImplement implements PublicationDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private PlayDAO playDAO;
	@Autowired
	private AlbumDAO albumDAO;
	
	
	
	public long getCount(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Publication.class)));
		
		return em.createQuery(cq).getSingleResult();
		
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Publication_.created, Publication_.modified, Publication_.id);
	}
	
	public List<Publication> getPublications(Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		criteria.select(publicationRoot);
		
		TypedQuery<Publication> query = page.createQuery(em, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
			Hibernate.initialize(publication.getProfile());
			Hibernate.initialize(publication.getAlbum());
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getPost());
			
		}
		
		return publications;
		
	}
	
	public List<Publication> getProfilePublications(Profile profile, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		criteria.select(publicationRoot);
		
		Predicate condition = cb.equal(publicationRoot.get(Publication_.profile), profile);
		criteria.where(condition);
		
		TypedQuery<Publication> query = page.createQuery(em, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
//			Hibernate.initialize(publication.getProfile());
//			Hibernate.initialize(publication.getAlbum());
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getPost());
			Hibernate.initialize(publication.getPost().getUploads());
		}
		
		return publications;
		
	}
	
	public List<Publication> searchByTerm(String term){
		
		List<Publication> publications = new ArrayList<Publication>();
		
		List<Post> posts = postDAO.searchByTerm(term);
		for(Post post: posts){
			publications.add(post.getPublication());
		}
		List<Play> plays = playDAO.searchByTerm(term);
		for(Play play : plays){
			publications.add(play.getPublication());
		}
		List<Album> albums = albumDAO.searchByTerm(term);
		for(Album album : albums){
			publications.add(album.getPublication());
		}
		
		return publications;
	}
	
	public Publication getPublication(int id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		criteria.select(publicationRoot);
		Predicate condition = cb.equal(publicationRoot.get(Publication_.id), id);
		criteria.where(condition);
		TypedQuery<Publication> query = em.createQuery(criteria);
		Publication publication = query.getSingleResult();
		
		return publication;
	}
	
	public List<Publication> getFeaturedPublications(Page page){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		Predicate condition = cb.equal(publicationRoot.get(Publication_.featured), true);
		criteria.select(publicationRoot).where(condition);
		
		TypedQuery<Publication> query = page.createQuery(em	, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
			Hibernate.initialize(publication.getProfile());
//			Hibernate.initialize(publication.getAlbum());
//			Hibernate.initialize(publication.getAlbum().getUploads());
//			Hibernate.initialize(publication.getPlay());
//			Hibernate.initialize(publication.getPlay().getPlayType());
//			Hibernate.initialize(publication.getPost());
//			Hibernate.initialize(publication.getPost().getUploads());
//			Hibernate.initialize(publication.getPost().getCategories());
			
		}
		
		return publications;
	}
}
