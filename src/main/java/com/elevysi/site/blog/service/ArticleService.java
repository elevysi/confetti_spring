package com.elevysi.site.blog.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.ArticleDAO;
import com.elevysi.site.blog.entity.Article;

public class ArticleService{
	
//	@Autowired
//	private ArticleDAO articleDAO;
//	
//	public Article save(Article article){
//		Date now = new Date();
//		article.setCreated(now);
//		article.setModified(now);
//		Article savedArticle =  articleDAO.saveArticle(article);
//		return savedArticle;
//	}
//	
//	@PreAuthorize("#article.seller.id == principal.activeProfile.id || hasRole('ADMIN')")
//	public Article saveEdited(Article article){
//		Date now = new Date();
//		article.setModified(now);
//		
//		Article savedArticle =  articleDAO.saveEditedArticle(article);
////		saveRelated(savedDossier);
//		
//		return savedArticle;
//	}
}
