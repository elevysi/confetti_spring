package com.elevysi.site.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.repository.DossierDAO;

@Service
public class DossierService extends AbstractService{
	
	@Autowired
	private DossierDAO dossierDAO;
	
	@Autowired
	private UploadService uploadService;
	
	public Dossier save(Dossier dossier){
		Date now = new Date();
		dossier.setCreated(now);
		dossier.setModified(now);
		dossier.setIsFeatured(false);
		
		Publication publication = savePublication(dossier.getProfile(), dossier.getName());
		if(publication != null){
			dossier.setPublication(publication);
		}
		
		Dossier savedDossier =  dossierDAO.saveDossier(dossier);
		saveRelated(savedDossier);
		
		return savedDossier;
	}
	
	@PreAuthorize("#dossier.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public Dossier saveEdited(Dossier dossier){
		Date now = new Date();
		dossier.setModified(now);
		
		Dossier savedDossier =  dossierDAO.saveEditedDossier(dossier);
		saveRelated(savedDossier);
		
		return savedDossier;
	}
	
	public void saveRelated(Dossier savedDossier){
		String uuid = savedDossier.getUuid();
		if(savedDossier != null){
			if(uuid != null){
				List<Upload> relatedUploads = uploadService.findByUuidAndDisplay(uuid, true);
				
				if(relatedUploads != null){
					for (Upload upload : relatedUploads) {
						uploadService.addItemTable(upload, savedDossier.getId(), "dossiers");
					}
				}				
			}
		}
	}
	
	public Dossier findById(int id){
		return dossierDAO.getDossier(id);
		
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return dossierDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Dossier> getDossiers(Page page){
		return dossierDAO.getDossiers(page);
	}
	
	@PreAuthorize("#dossier.profile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void deleteDossier(Dossier dossier){
		dossierDAO.deleteDossier(dossier.getId());
	}
	
	public List<Dossier> getDossiersForProfile(Profile profile, Page page){
		return dossierDAO.getDossiersForProfile(profile, page);
	}

}
