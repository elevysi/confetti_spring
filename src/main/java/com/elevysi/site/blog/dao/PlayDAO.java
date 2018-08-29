package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface PlayDAO extends AbstractDAO<Play, Integer>{
	
	public List<Play> searchFor(String term);
	public List<Play> searchByTerm(String term);
	public List<Play> getPlays(Page page);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public List<Play> getAllLatestForProfile(ProfileDTO profile, Page page);
	public Play findByID(Integer id);
}
