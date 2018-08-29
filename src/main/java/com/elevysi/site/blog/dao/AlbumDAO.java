package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface AlbumDAO {
	
	public List<Album> searchFor(String term);
	public List<Album> searchByTerm(String term);
	public List<Album> getAlbums(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Album findById(int id);
	public void deleteAlbum(int id);
	public List<Album> getAlbumsForProfile(ProfileDTO profile, Page page);

}
