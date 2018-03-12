package com.elevysi.site.blog.service;

import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Tag;
import com.elevysi.site.blog.repository.TagRepository;

@Service
public class TagService {
	private TagRepository tagRepository;
	
	public void saveTag(Tag tag){
		tagRepository.save(tag);
	}
}
