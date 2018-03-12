package com.elevysi.site.blog.form;

import java.util.ArrayList;
import java.util.List;

import com.elevysi.site.blog.pojo.PublicationMock;

public class AdminFeatured {
	
	private String searchField;
	private List<PublicationMock> featuredPublications = new ArrayList<PublicationMock>();
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public List<PublicationMock> getFeaturedPublications() {
		return featuredPublications;
	}
	public void setFeaturedPublications(List<PublicationMock> featuredPublications) {
		this.featuredPublications = featuredPublications;
	}
	
	

}
