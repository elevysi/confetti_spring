package com.elevysi.site.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.entity.Article;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

public interface ArticleDAO {
	
	
	public List<Article> searchByTerm(String term);
	public List<Article> getArticles(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Article getArticle(int id);
	public void deleteArticle(int id);
	public Article saveArticle(Article article);
	public Article saveEditedArticle(Article article);

}
