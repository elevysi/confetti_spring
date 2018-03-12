package com.elevysi.site.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.MediaKind;
import com.elevysi.site.blog.repository.MediaKindRepository;

@Service
public class MediaKindService {

	@Autowired
	private MediaKindRepository mediaKindRepository;

	public MediaKind findOneWithId(Integer id) {
		
		return mediaKindRepository.findOne(id);
	}

	public List<MediaKind> findAll() {
		
		return mediaKindRepository.findAll();
	}
}
