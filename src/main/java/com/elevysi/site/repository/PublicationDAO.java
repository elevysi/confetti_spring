package com.elevysi.site.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

public interface PublicationDAO {
	
	
	public long getCount();
	public Publication getPublication(int id);
	public List<Publication> getPublications(Page page);
	public List<Publication> getProfilePublications(Profile profile, Page page);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public List<Publication> searchByTerm(String term);
	public List<Publication> getFeaturedPublications(Page page);
}
