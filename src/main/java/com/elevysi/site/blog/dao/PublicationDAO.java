package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface PublicationDAO extends AbstractDAO<Publication, Long> {
	
	
	public List<Publication> getPublications(Page page);
	public List<Publication> getProfilePublications(Integer profileID, Page page);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public List<Publication> searchByTerm(String term);
	public List<Publication> getFeaturedPublications(Page page);
	public Publication findByID(Long id);
}
