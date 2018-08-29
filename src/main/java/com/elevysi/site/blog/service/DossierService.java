package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.DossierDAO;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class DossierService extends BasicService{
	
	@Autowired
	private DossierDAO dossierDAO;
	
	
	@Autowired
	private PublicationService publicationService;
	
	public Dossier save(Dossier dossier){
		Date now = new Date();
		dossier.setCreated(now);
		dossier.setModified(now);
		dossier.setIsFeatured(false);
		
		Publication publication = dossier.getPublication();
		publication.setCreated(now);
		publication.setModified(now);
		publication.setPublicPublication(true);
		publication.setProfileID(dossier.getProfileID());
		String slug = toSlug(dossier.getName());
		if(slug != null){
			publication.setFriendlyUrl(slug);
		}
		
		Dossier savedDossier =  dossierDAO.save(dossier);
		if(savedDossier != null){
			publicationService.saveRelated(savedDossier.getPublication().getId().intValue(), savedDossier.getUuid());
		}
		
		return savedDossier;
	}
	
	@PreAuthorize("#dossier.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public Dossier saveEdited(Dossier dossier){
		Date now = new Date();
		dossier.setModified(now);
		dossier.getPublication().setModified(now);
		
		Dossier savedDossier =  dossierDAO.saveEdited(dossier);
		if(savedDossier != null){
			publicationService.saveRelated(savedDossier.getPublication().getId().intValue(), savedDossier.getUuid());
		}
		
		return savedDossier;
	}
	
	
	
	public Dossier findByID(int id){
		return dossierDAO.findByID(id);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return dossierDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Dossier> getDossiers(Page page){
		return dossierDAO.getDossiers(page);
	}
	
	@PreAuthorize("#dossier.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void delete(Dossier dossier){
		dossierDAO.delete(dossier.getId());
	}
	
	public List<Dossier> getDossiersForProfile(ProfileDTO profile, Page page){
		return dossierDAO.getDossiersForProfile(profile, page);
	}
	
	public List<Dossier> findAll(){
		return dossierDAO.findAll();
	}

}
