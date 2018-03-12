package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface UploadDAO {
	
	public Upload getUpload(int id);
	public List<Upload> getUploads(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Upload deleteUpload(int id);
	public List<Upload> findByUploadOwner(Profile owningProfile, Page page);
	public List<Upload> findAllAbumUploads(Album album);
	public List<Upload> findPaginatedAlbumUploads(Album album, Page page);
	public void findAllAbumUploadsForUpdate(Album album, Boolean display);
	public void updateUploadForDisplay(int id, boolean display);

}
