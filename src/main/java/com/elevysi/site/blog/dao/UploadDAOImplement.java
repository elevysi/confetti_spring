package com.elevysi.site.blog.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Upload_;

@Repository
@Transactional
public class UploadDAOImplement extends AbstractDAOImpl<Upload, Integer> implements UploadDAO{
	
	@PersistenceContext
	EntityManager em;
	
	
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
	
	public List<Upload> findByUploadOwner(Integer profileID, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		Predicate condition = cb.equal(uploadRoot.get(Upload_.profileID), profileID);
		criteria.where(condition);
		
		TypedQuery<Upload> query = page.createQuery(em, criteria, uploadRoot);
		List<Upload> uploads =  query.getResultList();
		
		return uploads;
		
	}
	
	public List<Upload> findAllAbumUploads(Album album){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate condition1 = cb.equal(uploadRoot.get(Upload_.album), album);
		Predicate condition2 = cb.equal(uploadRoot.get(Upload_.display), true);
		predicates.add(cb.and(condition1, condition2));
		criteria.where(predicates.toArray(new Predicate[] {}));
		
		TypedQuery<Upload> query = em.createQuery(criteria);
		List<Upload> uploads =  query.getResultList();
		
		return uploads;
	}
	
	public List<Upload> findPaginatedAlbumUploads(Album album, Page page){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate condition1 = cb.equal(uploadRoot.get(Upload_.album), album);
		Predicate condition2 = cb.equal(uploadRoot.get(Upload_.display), true);
		predicates.add(cb.and(condition1, condition2));
		criteria.where(predicates.toArray(new Predicate[] {}));

		
		TypedQuery<Upload> query = page.createQuery(em, criteria, uploadRoot);
		List<Upload> uploads =  query.getResultList();
		
		return uploads;
	}
	
	public void findAllAbumUploadsForUpdate(Album album, Boolean display){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate condition1 = cb.equal(uploadRoot.get(Upload_.album), album);
		Predicate condition2 = cb.equal(uploadRoot.get(Upload_.display), true);
		predicates.add(cb.and(condition1, condition2));
		criteria.where(predicates.toArray(new Predicate[] {}));
		
		TypedQuery<Upload> query = em.createQuery(criteria);
		List<Upload> uploads =  query.getResultList();
		if(uploads != null){
			for(Upload upload : uploads){
				upload.setDisplay(display);
				Date now = new Date();
				upload.setModified(now);
				em.merge(upload);
			}
				
		}
	}
	
	public void updateUploadForDisplay(int id, boolean display){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		Predicate condition = cb.equal(uploadRoot.get(Upload_.id), id);
		criteria.where(condition);
		TypedQuery<Upload> query = em.createQuery(criteria);
		Upload upload = query.getSingleResult();
		Date now = new Date();
		upload.setDisplay(display);
		upload.setModified(now);
		em.merge(upload);
	}
	

	public List<Upload> findByLinkIDAndLinkTable(int linkID, String linkTable){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Upload> criteria = cb.createQuery(Upload.class);
		
		Root<Upload> uploadRoot = criteria.from(Upload.class);
		criteria.select(uploadRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate condition1 = cb.equal(uploadRoot.get(Upload_.linkId), linkID);
		Predicate condition2 = cb.equal(uploadRoot.get(Upload_.linkTable), linkTable);
		predicates.add(cb.and(condition1, condition2));
		criteria.where(predicates.toArray(new Predicate[] {}));
		
		TypedQuery<Upload> query = em.createQuery(criteria);
		return  query.getResultList();
	}
}
