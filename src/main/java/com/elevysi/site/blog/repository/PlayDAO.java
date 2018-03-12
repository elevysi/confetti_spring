package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface PlayDAO {
	
	public List<Play> searchFor(String term);
	public List<Play> searchByTerm(String term);
	public List<Play> getPlays(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Play getPlay(int id);
	public void deletePlay(int id);
	public List<Play> getAllLatestForProfile(Profile profile, Page page);
}
