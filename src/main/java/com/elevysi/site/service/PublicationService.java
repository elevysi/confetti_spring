package com.elevysi.site.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.repository.PublicationDAO;
import com.elevysi.site.repository.PublicationDAOImplement;
import com.elevysi.site.repository.PublicationRepository;

@Service
public class PublicationService{
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	
	public Publication findOne(Integer id){
		return publicationRepository.findOne(id);
	}
	

	public Publication findPlayPublication(Play play){
		return publicationRepository.findPlayPublication(play.getId());
	}
	
	public Publication findPostPublication(Post post){
		return publicationRepository.findPostPublication(post.getId());
	}
	
	public Publication findAlbumPublication(Album album){
		return publicationRepository.findAlbumPublication(album.getId());
	}
	
	public Publication saveRemotePublication(Publication publication){
		return publicationRepository.save(publication);
	}


	public Page<Publication> findProfilePublications(Profile actingProfile, Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		 return publicationRepository.findProfilePublications(actingProfile.getId(), constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		
	}
	
	/**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByField(String sortField, String sortDirection) {
    	
    	if(sortDirection.equalsIgnoreCase("desc")){
    		return new Sort(Sort.Direction.DESC, sortField);
    	}else return new Sort(Sort.Direction.ASC, sortField);
        
    }
	
    private Pageable constructPageSpecification(int pageIndex, int limit,  String sortField, String sortDirection) {
    	Pageable pageSpecification = new PageRequest(pageIndex, limit, sortByField(sortField, sortDirection));
        return pageSpecification;
    }
	
	public Publication savePublication(Publication publication){
		Date now = new Date();
		publication.setCreated(now);
		publication.setModified(now);
		return publicationRepository.save(publication);
	}
	
	public Publication saveEditedPublication(Publication publication){
		Date now = new Date();
		publication.setModified(now);
		return this.doSaveEditedPublication(publication);
	}
	
	public Publication doSaveEditedPublication(Publication publication){
		return publicationRepository.save(publication);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return publicationDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Publication> getAllPublications(com.elevysi.site.pojo.Page page){
		return publicationDAO.getPublications(page);
	}

	public List<Publication> getProfilePublication(Profile profile, com.elevysi.site.pojo.Page page){
		return publicationDAO.getProfilePublication(profile, page);
	}
	
	public List<Publication> searchByTerm(String term){
		return publicationDAO.searchByTerm(term);
	}
	
	public Publication getPublication(int id){
		return publicationDAO.getPublication(id);
	}
	
	public List<Publication> getFeaturedPublications(com.elevysi.site.pojo.Page page){
		return publicationDAO.getFeaturedPublications(page);
	}
	
	/**
	 * http://stackoverflow.com/questions/1657193/java-code-library-for-generating-slugs-for-use-in-pretty-urls
	 * @param input
	 * @return
	 */
	public String toSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
    }
	
	public Publication updateItemPublicationWithSlug(int id, String toSlug){
		Publication publication = getPublication(id);
		if(publication != null){
			Date now = new Date();
			publication.setModified(now);
			String slug = toSlug(toSlug);
			publication.setFriendlyUrl(slug);
			return publicationRepository.save(publication);
		}
		return null;
	}
}
