package com.elevysi.site.blog.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.metamodel.SingularAttribute;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.PublicationDAO;
import com.elevysi.site.blog.dao.PublicationDAOImplement;
import com.elevysi.site.blog.dao.PublicationRepository;
import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.commons.dto.DossierDTO;
import com.elevysi.site.commons.dto.PlayDTO;
import com.elevysi.site.commons.dto.PostDTO;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.dto.PublicationDTO;
import com.elevysi.site.commons.dto.UploadDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class PublicationService extends BasicService{
	
//	@Autowired
//	private PublicationRepository publicationRepository;
	
	
	private PublicationDAO publicationDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	public PublicationService(PublicationDAO publicationDAO, ModelMapper modelMapper){
		this.publicationDAO = publicationDAO;
		this.modelMapper = modelMapper;
	}
	
	public Publication findByID(Long id){
		return publicationDAO.findByID(id);
	}
	

//	public Publication findPlayPublication(Play play){
//		return publicationRepository.findPlayPublication(play.getId());
//	}
//	
//	public Publication findPostPublication(Post post){
//		return publicationRepository.findPostPublication(post.getId());
//	}
//	
//	public Publication findAlbumPublication(Album album){
//		return publicationRepository.findAlbumPublication(album.getId());
//	}
	
	public Publication findProfilePublication(ProfileDTO profile){
//		return publicationRepository.findProfilePublication(profile.getId());
		
		return null;
	}
	
//	public Publication findDossierPublication(Dossier dossier){
//		return publicationRepository.findDossierPublication(dossier.getId());
//	}
//	
//	public Publication saveRemotePublication(Publication publication){
//		return publicationRepository.save(publication);
//	}
	
	/**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByField(String sortField, String sortDirection) {
    	
    	if(sortDirection.equalsIgnoreCase("desc")){
    		return new Sort(Sort.Direction.DESC, sortField);
    	}else return new Sort(Sort.Direction.ASC, sortField);
        
    }
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return publicationDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Publication> getAllPublications(com.elevysi.site.commons.pojo.Page page){
		return publicationDAO.getPublications(page);
	}

	public List<Publication> getProfilePublications(Integer profileID, com.elevysi.site.commons.pojo.Page page){
		return publicationDAO.getProfilePublications(profileID, page);
	}
	
	public List<PublicationDTO> getProfilePublicationsDTO(Integer profileID, com.elevysi.site.commons.pojo.Page page){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Publication> publications = getProfilePublications(profileID, page);
		java.lang.reflect.Type targetListType = new TypeToken<ArrayList<PublicationDTO>>() {}.getType();
		List<PublicationDTO> publicationsDTO = modelMapper.map(publications, targetListType);
				
		return publicationsDTO;
	}
	
	public List<Publication> searchByTerm(String term){
		return publicationDAO.searchByTerm(term);
	}
	
	public Publication getPublication(Long id){
		return publicationDAO.findByID(id);
	}
	
	public List<Publication> getFeaturedPublications(com.elevysi.site.commons.pojo.Page page){
		return publicationDAO.getFeaturedPublications(page);
	}
	
	public Publication updateItemPublicationWithSlug(Long id, String toSlug){
		Publication publication = getPublication(id);
		if(publication != null){
			Date now = new Date();
			publication.setModified(now);
			String slug = toSlug(toSlug);
			publication.setFriendlyUrl(slug);
			return publicationDAO.save(publication);
		}
		return null;
	}
	
	public Publication saveEditedPublication(Publication publication){
		Date now = new Date();
		publication.setModified(now);
		return publicationDAO.saveEdited(publication);
	}
	
	public Publication save(Publication publication){
		Date now = new Date();
		publication.setCreated(now);
		publication.setModified(now);
		return publicationDAO.save(publication);
	}
	
	public List<Publication> findAll(){
		return publicationDAO.findAll();
	}
	
	public void saveRelated(Integer itemID, String UUID){
		List<Upload> relatedUploads = uploadService.findByUuidAndDisplay(UUID, true);
		
		if(relatedUploads != null){
			for (Upload upload : relatedUploads) {
				uploadService.addItemTable(upload, itemID, "publication");
			}
		}		
	}
}
