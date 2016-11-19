package com.elevysi.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Category;
import com.elevysi.site.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	public Category findOne(Integer category_id) {
		
		return categoryRepository.findById(category_id);
	}

}
