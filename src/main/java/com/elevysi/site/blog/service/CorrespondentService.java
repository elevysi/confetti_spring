package com.elevysi.site.blog.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Correspondent;
import com.elevysi.site.blog.repository.CorrespondentRepository;

@Service
public class CorrespondentService {

	@Autowired
	private CorrespondentRepository correspondentRepository;
	
	public Correspondent saveCorrespondent(Correspondent correspondent) {
		Date now = new Date();
		correspondent.setCreated(now);
		correspondent.setModified(now);
		return correspondentRepository.save(correspondent);
	}

}
