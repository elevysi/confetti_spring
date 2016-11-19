package com.elevysi.site.service;

import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Tag;
import com.elevysi.site.repository.TagRepository;

@Service
public class TagService {
	private TagRepository tagRepository;
	
	public void saveTag(Tag tag){
		tagRepository.save(tag);
	}
}
