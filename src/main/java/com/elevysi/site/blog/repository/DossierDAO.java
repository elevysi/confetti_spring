package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface DossierDAO{
	
	public Dossier getDossier(int id);
	public Dossier saveDossier(Dossier dossier);
	public List<Dossier> getDossiers(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Dossier saveEditedDossier(Dossier dossier);
	public void deleteDossier(int id);
	public List<Dossier> getDossiersForProfile(Profile profile, Page page);
	public List<Dossier> searchByTerm(String term);

}
