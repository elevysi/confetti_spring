package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.PlayDAO;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Service
public class PlayService extends BasicService{
	
	@Autowired
	private PlayDAO playDAO;

	public Play savePlay(Play play) {
		
		Date now = new Date();
		play.setCreated(now);
		play.setModified(now);
		play.setFeatured(true);
		
		play.getPublication().setCreated(now);
		play.getPublication().setModified(now);
		play.getPublication().setPublicPublication(true);
		
		String slug = toSlug(play.getTitle());
		if(slug != null){
			play.getPublication().setFriendlyUrl(slug);
		}
		
		return playDAO.save(play);
		
	}
	
	@PreAuthorize("#play.playProfile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public Play saveEditedPlay(Play play) {
		
		Date now = new Date();
		play.setModified(now);
		
		if(play.getCreated() == null){
			play.setCreated(now);
		}
		
		return this.doSaveEditedPlay(play);
		
	}
	
	public Play doSaveEditedPlay(Play play){
		
		/**
		 * Save edited slug
		 */
		saveEditedPublication(play.getPublication().getId(), play.getTitle());
		
		return playDAO.save(play);
	}

	public List<Play> findLatestPlaysForProfile(ProfileDTO playOwner, Play play, Integer pageNumber, Integer limit,
			String sortField, String sortDirection) {
		
//		Page<Play> requestedPage;
//		Integer viewedPlayId = play.getId();
//		if(viewedPlayId != null){
//			requestedPage = playRepository.findAllLatestForProfileExcept(playOwner.getId(), viewedPlayId, constructPageSpecification(pageNumber, limit, sortField, sortDirection));
//		}else{
//			requestedPage = playRepository.findAllLatestForProfile(playOwner.getId(), constructPageSpecification(pageNumber, limit, sortField, sortDirection));
//		}
		
//		return requestedPage.getContent();
		
		return null;
	}
	
	public List<Play> getProfilePlays(ProfileDTO profile, com.elevysi.site.commons.pojo.Page page){
		return playDAO.getAllLatestForProfile(profile, page);
	}
	
	public List<Play> getPlays(com.elevysi.site.commons.pojo.Page page){
		return playDAO.getPlays(page);
	}

	
	public Play updatePlay(Play play){
		return playDAO.save(play);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return playDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public Play findByID(int id){
		return playDAO.findByID(id);
	}
	
	@PreAuthorize("#play.playProfile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void delete(Play play){
		playDAO.delete(play.getId());
	}

	public List<Play> findAll(){
		return playDAO.findAll();
	}
}
