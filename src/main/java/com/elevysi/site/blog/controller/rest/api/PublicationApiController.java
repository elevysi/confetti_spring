package com.elevysi.site.blog.controller.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elevysi.site.blog.entity.Publication_;
import com.elevysi.site.blog.service.PublicationService;
import com.elevysi.site.commons.dto.PublicationDTO;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@RestController
@RequestMapping("/api/publications")
public class PublicationApiController extends ApiController{
	
	@Autowired
	private PublicationService publicationService;
	
	@RequestMapping("/profile/{profileID}")
	public @ResponseBody List<PublicationDTO> profilePublications(
			@PathVariable("profileID")Integer profileID,
			@RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		OffsetPage page = publicationService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Publication_.modified, SortDirection.DESC);
		return publicationService.getProfilePublicationsDTO(profileID, page);
	}

}
