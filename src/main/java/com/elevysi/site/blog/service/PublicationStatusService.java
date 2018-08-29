package com.elevysi.site.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.PublicationStatusDAO;
import com.elevysi.site.blog.entity.PublicationStatus;

@Service
public class PublicationStatusService extends AbstractServiceImpl<PublicationStatus, Integer>{
	
	@Autowired
	private PublicationStatusDAO publicationStatusDAO;

		
	public PublicationStatus findDraftPostStatus(){
		return publicationStatusDAO.findByID(1);
	}
	
	public PublicationStatus findPublishedPostStatus(){
		return publicationStatusDAO.findByID(2);
	}
	
	
	public PublicationStatus findToBePublishedPostStatus(){
		return publicationStatusDAO.findByID(3);
	}
	

}
