package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface UploadDAO extends AbstractDAO<Upload, Integer>{
	
	public List<Upload> getUploads(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Upload deleteUpload(int id);
	public List<Upload> findByUploadOwner(Integer profileID, Page page);
	public List<Upload> findAllAbumUploads(Album album);
	public List<Upload> findPaginatedAlbumUploads(Album album, Page page);
	public void findAllAbumUploadsForUpdate(Album album, Boolean display);
	public void updateUploadForDisplay(int id, boolean display);
	public List<Upload> findByLinkIDAndLinkTable(int linkID, String linkTable);

}
