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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.Upload_;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class UploadDAOImplement implements UploadDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public Upload getUpload(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		Predicate condition = cb.equal(uploadRoot.get(Upload_.id), id);
		criteria.where(condition);
		TypedQuery<Upload> query = em.createQuery(criteria);
		Upload upload = query.getSingleResult();
		
		return upload;
		
	}
	public List<Upload> getUploads(Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		Root<Upload> upload = criteria.from(Upload.class);
		criteria.select(upload);
		
		TypedQuery<Upload> query = page.createQuery(em, criteria, upload);
		List<Upload> uploads =  query.getResultList();
		
//		for(Post postItem : posts){
//			Hibernate.initialize(postItem.getUploads());
//			Hibernate.initialize(postItem.getProfile());
//			Hibernate.initialize(postItem.getCategories());
//		}
		
		return uploads;
		
	}
	public long getCount(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Upload.class)));
		
		return em.createQuery(cq).getSingleResult();
		
	}
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Upload_.created, Upload_.modified, Upload_.id, Upload_.title, Upload_.filename);
		
	}
	public Upload deleteUpload(int id){
		return null;
	}
	
	public List<Upload> findByUploadOwner(Profile owningProfile, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		Predicate condition = cb.equal(uploadRoot.get(Upload_.uploadOwner), owningProfile);
		criteria.where(condition);
		
		TypedQuery<Upload> query = page.createQuery(em, criteria, uploadRoot);
		List<Upload> uploads =  query.getResultList();
		
		return uploads;
		
	}
	
	public List<Upload> findAllbumUploads(Album album){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		Predicate condition = cb.equal(uploadRoot.get(Upload_.album), album);
		criteria.where(condition);
		
		TypedQuery<Upload> query = em.createQuery(criteria);
		List<Upload> uploads =  query.getResultList();
		
		return uploads;
	}

}
