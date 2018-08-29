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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Publication_;

@Repository
@Transactional
public class PublicationDAOImplement extends AbstractDAOImpl<Publication, Long> implements PublicationDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private PlayDAO playDAO;
	@Autowired
	private DossierDAO dossierDAO;
	
//	public PublicationDAOImplement(Class<Publication> type){
//		super(type);
//	}
	
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Publication_.created, Publication_.modified, Publication_.id);
	}
	
	public List<Publication> getPublications(Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		criteria.select(publicationRoot);
		
		TypedQuery<Publication> query = page.createQuery(em, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
			Hibernate.initialize(publication.getAlbum());
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getPost());
			Hibernate.initialize(publication.getDossier());
			
		}
		
		return publications;
		
	}
	
	public List<Publication> getProfilePublications(Integer profileID, Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		criteria.select(publicationRoot);
		
		Predicate condition = cb.equal(publicationRoot.get(Publication_.profileID), profileID);
		criteria.where(condition);
		
		TypedQuery<Publication> query = page.createQuery(em, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
			Hibernate.initialize(publication.getProfile());
			Hibernate.initialize(publication.getAlbum());
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getPost());
			Hibernate.initialize(publication.getDossier());
			Hibernate.initialize(publication.getUploads());
		}
		
		return publications;
		
	}
	
	public List<Publication> searchByTerm(String term){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		List<Publication> publications = new ArrayList<Publication>();
		
		List<Post> posts = postDAO.searchByTerm(term);
		for(Post post: posts){
			publications.add(post.getPublication());
		}
		List<Play> plays = playDAO.searchByTerm(term);
		for(Play play : plays){
			publications.add(play.getPublication());
		}
//		List<Album> albums = albumDAO.searchByTerm(term);
//		for(Album album : albums){
//			publications.add(album.getPublication());
//		}
		
		List<Dossier> dossiers = dossierDAO.searchByTerm(term);
		for(Dossier dossier : dossiers){
			publications.add(dossier.getPublication());
		}
		
		return publications;
	}
	
	
	public List<Publication> getFeaturedPublications(Page page){
		
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("itemTableIs");
		filter.setParameter("link_tableValue", "publication");
		filter.setParameter("displayValue", 1);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Publication> criteria = cb.createQuery(Publication.class);
		Root<Publication> publicationRoot = criteria.from(Publication.class);
		Predicate condition = cb.equal(publicationRoot.get(Publication_.featured), true);
		criteria.select(publicationRoot).where(condition);
		
		TypedQuery<Publication> query = page.createQuery(em	, criteria, publicationRoot);
		List<Publication> publications =  query.getResultList();
		
		for(Publication publication : publications){
//			publication.setProfile(getProfile(publication.getProfileID()));
			
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getPost());
			Hibernate.initialize(publication.getDossier());
			Hibernate.initialize(publication.getUploads());
			Hibernate.initialize(publication.getCategories());
			Hibernate.initialize(publication.getDossier());
		}
		
		return publications;
	}
	
	@Override
	public Publication findByID(Long id){
		Publication publication = em.find(Publication.class, id);
		if(publication != null){
			Hibernate.initialize(publication.getPost());
			Hibernate.initialize(publication.getPlay());
			Hibernate.initialize(publication.getDossier());
		}
		
		return publication;
	}
	
	
}
