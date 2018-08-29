package com.elevysi.site.blog.service;

import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.TagRepository;
import com.elevysi.site.blog.entity.Tag;

@Service
public class TagService {
	private TagRepository tagRepository;
	
	public void saveTag(Tag tag){
		tagRepository.save(tag);
	}
}
