package com.elevysi.site.blog.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

public interface DossierDAO extends AbstractDAO<Dossier, Integer>{
	
	public List<Dossier> getDossiers(Page page);
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public List<Dossier> getDossiersForProfile(ProfileDTO profile, Page page);
	public List<Dossier> searchByTerm(String term);
	public Dossier findByID(Integer id);

}
