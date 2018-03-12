package com.elevysi.site.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.PostStatus;
import com.elevysi.site.blog.repository.PostStatusRepository;

@Service
public class PostStatusService {
	
	@Autowired
	private PostStatusRepository postStatusRepository;

		
	public PostStatus findDraftPostStatus(){
		return postStatusRepository.findById(1);
	}
	
	public PostStatus findPublishedPostStatus(){
		return postStatusRepository.findById(2);
	}
	
	
	public PostStatus findToBePublishedPostStatus(){
		return postStatusRepository.findById(3);
	}
	

}
