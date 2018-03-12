package com.elevysi.site.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.ProfileType;
import com.elevysi.site.blog.repository.ProfileTypeRepository;

@Service
public class ProfileTypeService {

	@Autowired
	private ProfileTypeRepository profileTypeRepository;

	public ProfileType getProfileType(String name){
		
		return profileTypeRepository.findByName(name);
		
	}

	public ProfileType findOne(String name) {
		return profileTypeRepository.findByName(name);
	}
	

	public List<ProfileType> findProfileTypes(){
		return profileTypeRepository.findAll();
	}
}
