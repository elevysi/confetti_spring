package com.elevysi.site.blog.repository;

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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Article;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Article_;

@Repository
@Transactional
public class ArticleDAOImplement implements ArticleDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	
	public List<Article> searchByTerm(String term){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = cb.createQuery(Article.class);
		Root<Article> playRoot = criteria.from(Article.class);
		criteria.select(playRoot);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.like(playRoot.get(Article_.name), "%"+term+"%"));
		predicates.add(cb.like(playRoot.get(Article_.description), "%"+term+"%"));
		
		criteria.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		TypedQuery<Article> query = em.createQuery(criteria);
		List<Article> articles =  query.getResultList();
		
		
		return articles;
	}
	public List<Article> getArticles(Page page){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = cb.createQuery(Article.class);
		Root<Article> articleRoot = criteria.from(Article.class);
		criteria.select(articleRoot);
		
		TypedQuery<Article> query = page.createQuery(em, criteria, articleRoot);
		List<Article> articles =  query.getResultList();
		
		
		
		return articles;
	}
	
	public long getCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Article.class)));
		
		return em.createQuery(cq).getSingleResult();
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Article_.created, Article_.modified, Article_.id, Article_.name, Article_.description);
	}
	
	public Article getArticle(int id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = cb.createQuery(Article.class);
		Root<Article> articleRoot = criteria.from(Article.class);
		criteria.select(articleRoot);
		Predicate condition = cb.equal(articleRoot.get(Article_.id), id);
		criteria.where(condition);
		TypedQuery<Article> query = em.createQuery(criteria);
		Article article = query.getSingleResult();
		
		return article;
	}
	
	public void deleteArticle(int id){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = cb.createQuery(Article.class);
		Root<Article> articleRoot = criteria.from(Article.class);
		criteria.select(articleRoot);
		Predicate condition = cb.equal(articleRoot.get(Article_.id), id);
		criteria.where(condition);
		TypedQuery<Article> query = em.createQuery(criteria);
		Article article = query.getSingleResult();
		
		em.merge(article);
		em.remove(article);
	}
	
	
	public Article saveArticle(Article article){
		em.persist(article);
		em.flush();
		return article;
		
	}
	
	public Article saveEditedArticle(Article article){
		em.merge(article);
		em.flush();
		return article;
		
	}
}
