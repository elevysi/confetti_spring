package com.elevysi.site.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.security.ActiveUser;


@Service
public abstract class AbstractService {
	
	@Autowired
	private PublicationService publicationService;
	
	@Value("${upload.img.path}")
	protected String avatarUploadPath;
	
	@Value("${ds}")
	protected String ds;
	
	/**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex The index of the wanted result page
     * @return
     */
    protected Pageable constructPageSpecification(int pageIndex, int limit,  String sortField, String sortDirection) {
    	Pageable pageSpecification = new PageRequest(pageIndex, limit, sortByField(sortField, sortDirection));
        return pageSpecification;
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
    
    protected Publication savePublication(Profile profile, String toSlug){
    	
    	/**
		 * Create Publication for this post
		 */
		Publication publication = new Publication();
		
		Date now = new Date();
		publication.setCreated(now);
		publication.setModified(now);
		publication.setPublicPublication(true);
		publication.setProfile(profile);
		String slug = publicationService.toSlug(toSlug);
		if(slug != null){
			publication.setFriendlyUrl(slug);
		}
    	return publicationService.savePublication(publication);
    	
    }
    
    protected Publication saveEditedPublication(int id, String toSlug){
    	return publicationService.updateItemPublicationWithSlug(id, toSlug);
    }
    
    
    public Profile getActiveProfile(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isAuthenticated =  this.isAuthed();
    	
    	if(auth != null && isAuthenticated){
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			Profile actingProfile = activeUser.getActiveProfile();
			if(actingProfile != null){
				return actingProfile;
			}
		}
    	
    	return null;
    }
    
    public ActiveUser getActiveUser(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isAuthenticated =  this.isAuthed();
    	
    	if(auth != null && isAuthenticated){
			return  (ActiveUser)auth.getPrincipal();
		}
    	
    	return null;
    }
   
    
    public boolean isAuthed(){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}
    	
    	return false;
    }
    
    public int getStart(int pageNo, int maxResults){
    	
    	return pageNo == maxResults * (pageNo - 1) ? 1 : maxResults * (pageNo - 1) + 1;
    	
    }
    
    public boolean isAdmin(){
		Profile active = getActiveProfile();
		if(active !=null){
//			List<Role>
//			if(active.getUser().getRoles()){
//				return true;
//			}
		}
    	return true;
	}

}
