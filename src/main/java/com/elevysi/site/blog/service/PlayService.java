package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.repository.PlayDAO;
import com.elevysi.site.blog.repository.PlayRepository;

@Service
public class PlayService extends AbstractService{

	@Autowired
	private PlayRepository playRepository;
	
	@Autowired
	private PlayDAO playDAO;

	public Play savePlay(Play play) {
		
		Date now = new Date();
		play.setCreated(now);
		play.setModified(now);
		
		play.setFeatured(true);
		
		Publication publication = savePublication(play.getPlayProfile(), play.getTitle());
		if(publication != null){
			play.setPublication(publication);
		}
		
		
		return playRepository.save(play);
		
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
		
		return playRepository.save(play);
	}
	
	public List<Play> findLatestFeaturedPlaysByType(Integer playTypeID, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		return findPaginatedFeaturedPlaysByType(playTypeID, pageNumber, limit, sortField, sortDirection).getContent();
	}
	
	public Page<Play> findPaginatedFeaturedPlaysByType(Integer playTypeID ,Integer pageNumber, Integer limit, String sortField, String sortDirection){
		Page<Play> requestedPage = playRepository.findFeaturedPlaywithType(playTypeID, constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));
		return requestedPage;
	}

	public Play loadPlay(Integer id) {
		
		return playRepository.loadViewPlay(id);
	}

	public List<Play> findLatestPlaysForProfile(Profile playOwner, Play play, Integer pageNumber, Integer limit,
			String sortField, String sortDirection) {
		
		Page<Play> requestedPage;
		Integer viewedPlayId = play.getId();
		if(viewedPlayId != null){
			requestedPage = playRepository.findAllLatestForProfileExcept(playOwner.getId(), viewedPlayId, constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		}else{
			requestedPage = playRepository.findAllLatestForProfile(playOwner.getId(), constructPageSpecification(pageNumber, limit, sortField, sortDirection));
		}
		
		return requestedPage.getContent();
	}
	
	public List<Play> getProfilePlays(Profile profile, com.elevysi.site.blog.pojo.Page page){
		return playDAO.getAllLatestForProfile(profile, page);
	}
	
	public List<Play> getPlays(com.elevysi.site.blog.pojo.Page page){
		return playDAO.getPlays(page);
	}

	public List<Play> findAllPlays() {
		
		return playRepository.findAll();
	}
	
	public Play updatePlay(Play play){
		return playRepository.save(play);
	}
	
	public List<Play> findAllPlaysWithProfile(){
		return playRepository.findAllPlaysWithProfile();
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return playDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public Play getPlay(int id){
		return playDAO.getPlay(id);
	}
	
	@PreAuthorize("#play.playProfile.id == principal.activeProfile.id || hasRole('ADMIN')")
	public void deletePlay(Play play){
		playDAO.deletePlay(play.getId());
	}

}
