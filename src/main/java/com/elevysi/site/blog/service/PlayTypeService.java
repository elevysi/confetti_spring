package com.elevysi.site.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.repository.PlayTypeRepository;

@Service
public class PlayTypeService {
	
	@Autowired
	private PlayTypeRepository playTypeRepository;
	
	public List<PlayType> findAll(){
		return playTypeRepository.findAll();
	}
	
	public PlayType findOne(Integer playTypeId){
		return playTypeRepository.findOne(playTypeId);
	}

}
