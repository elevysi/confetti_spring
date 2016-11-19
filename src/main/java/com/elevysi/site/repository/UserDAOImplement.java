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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.User;
import com.elevysi.site.entity.User_;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

@Repository
@Transactional
public class UserDAOImplement implements UserDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<User> searchByTerm(String term){
		
		TypedQuery<User> query = em.createQuery("SELECT user FROM User post WHERE "+
				"LOWER(user.first_name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(user.last_name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(user.username) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(user.email) LIKE LOWER(CONCAT('%',:searchTerm, '%'))",
						User.class
		).setParameter("searchTerm", term);
		
		List<User> users = query.getResultList();
		
		for(User user : users){
			Hibernate.initialize(user.getProfiles());
			Hibernate.initialize(user.getRoles());
		}
		
		return null;
	}
	
	public List<User> getUsers(Page page){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		
		TypedQuery<User> query = page.createQuery(em, criteria, userRoot);
		List<User> users =  query.getResultList();
		
		for(User user : users){
			Hibernate.initialize(user.getProfiles());
			Hibernate.initialize(user.getRoles());
		}
		
		return users;
	}
	
	public User findById(int id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> queryRoot = criteria.from(User.class);
		Predicate condition = cb.equal(queryRoot.get(User_.id), id);
		criteria.where(condition);
		TypedQuery<User> query = em.createQuery(criteria);
		User user = query.getSingleResult();
		
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getProfiles());
		
		
		return user;
	}
	
	public User loadByUsername(String username){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> queryRoot = criteria.from(User.class);
		Predicate condition = cb.equal(queryRoot.get(User_.username), username);
		criteria.where(condition);
		TypedQuery<User> query = em.createQuery(criteria);
		User user = query.getSingleResult();
		
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getProfiles());
		Hibernate.initialize(user.getProfiles());
		
		for(Profile profile: user.getProfiles()){
			Hibernate.initialize(profile.getProfileType());
			Hibernate.initialize(profile.getProfilePicture());
		}
		
		
		return user;
	}
	
	public long getCount(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(User.class)));
		
		return em.createQuery(cq).getSingleResult();
		
	}
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, User_.created, User_.modified, User_.id, User_.first_name, User_.last_name, User_.username);
	}
	
	public User update(User user){
		return em.merge(user);
	}
	
	public User findByUsername(String username){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> queryRoot = criteria.from(User.class);
		Predicate condition = cb.equal(queryRoot.get(User_.username), username);
		criteria.where(condition);
		TypedQuery<User> query = em.createQuery(criteria);
		User user = query.getSingleResult();
		
		return user;
	}
}
