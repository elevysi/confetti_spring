package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.AlbumDAO;
import com.elevysi.site.blog.dao.AlbumRepository;
import com.elevysi.site.blog.dao.UploadRepository;
import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class AlbumService extends BasicService{
	
	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	
	
	public List<Album> getPaginatedAlbumWithUploads(Album album, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		
		Page<Album> requestedPage = albumRepository.findAlbumWithUploads(album.getId(), constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));		
		return requestedPage.getContent();
		
	}
	
	
	public List<Album> getPaginatedAlbumsForProfile(ProfileDTO profile, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		
//		Page<Album> requestedPage = albumRepository.findProfileAlbums(profile.getId(), constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));		
//		return requestedPage.getContent();
		
		return null;
		
	}
	
	public List<Upload> getPaginatedAlbumUploads(Album album, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		
		Page<Upload> requestedPage = uploadRepository.findAlbumUploads(album.getId(), constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));		
		return requestedPage.getContent();
		
	}
	
	public Album saveAlbum(Album album) {
		Date now = new Date();
		album.setCreated(now);
		album.setModified(now);
		

		Publication publication = new Publication();
		publication.setCreated(now);
		publication.setModified(now);
		publication.setPublicPublication(true);
//		publication.setProfileID(album.getProfileID());
		publication.setProfileName(album.getProfileOwner().getName());
		String slug = toSlug(album.getName());
		if(slug != null){
			publication.setFriendlyUrl(slug);
		}
		
		Album savedAlbum = albumRepository.save(album);
		
		String uuid = savedAlbum.getUuid();
		if(uuid != null){
			List<Upload> relatedUploads = uploadService.findAllWithUuid(uuid);
			if(relatedUploads != null){
				if(relatedUploads.size() > 0){
					
					/**
					 * Create album with all uploads
					 */
					
					for (Upload upload : relatedUploads) {
						savedAlbum.addUpload(upload);
					}
										
					Album reSavedAlbum = albumRepository.save(savedAlbum);
					
					return reSavedAlbum;
					
					/**
					 * Get first Element of the album and make it the thumbnail
					 */
					
//					setAsPostImage(savedPost, relatedUploads.get(0));
					
					
				}
			}
		}
		
		return savedAlbum;
	}
	
	@PreAuthorize("#album.profileOwner.id == principal.activeProfile.id || hasRole('ADMIN')")
	public Album saveEditedAlbum(Album album) {
		Date now = new Date();
		album.setCreated(now);
		album.setModified(now);
		

//		Publication publication = savePublication(album.getProfileOwner());
//		if(publication != null){
//			album.setPublication(publication);
//		}
		
//		Album savedAlbum = albumRepository.save(album);
		
		String uuid = album.getUuid();
		if(uuid != null){
			List<Upload> relatedUploads = uploadService.findAllWithUuid(uuid);
			if(relatedUploads != null){
				if(relatedUploads.size() > 0){
					
					/**
					 * Create album with all uploads
					 */
					
					for (Upload upload : relatedUploads) {
						if(upload.isDisplay()){
							album.addUpload(upload);
						}else{
							album.removeUpload(upload);
						}
						
					}
					
					/**
					 * Get first Element of the album and make it the thumbnail
					 */
					
//					setAsPostImage(savedPost, relatedUploads.get(0));
					
					
				}
			}
		}
		
		Album reSavedAlbum = albumRepository.save(album);
		
		return reSavedAlbum;
	}
	
	
	
	public Album findOne(Integer id){
		return albumDAO.findById(id);
		
	}
	
	public Album findById(Integer id){
		return albumDAO.findById(id);
	}
	
	public List<Album> findAllProfileAlbums(){
//		return albumRepository.findAllProfileAlbums();
		
		return null;
	}


	public Album findAlbumWithProfile(Album album) {
		return albumRepository.findAlbumWithProfile(album.getId());
	}
	
	public Album updateAlbum(Album album){
		return albumRepository.save(album);
	}
	
	public Album findByLinkIdAndLinkTable(Integer linkId, String linkTable){
		return albumRepository.findByLinkIdAndLinkTable(linkId, linkTable);
	}


	public void delete(Album album) {
		albumRepository.delete(album);
		
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return albumDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Album> getAlbums(com.elevysi.site.commons.pojo.Page page){
		return albumDAO.getAlbums(page);
	}
	
	@PreAuthorize("#album.profileOwner.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void deleteAlbum(Album album){
		albumDAO.deleteAlbum(album.getId());
	}
	
	public List<Album> getAlbumsForProfile(ProfileDTO profile, com.elevysi.site.commons.pojo.Page page){
		return albumDAO.getAlbumsForProfile(profile, page);
	}

}
