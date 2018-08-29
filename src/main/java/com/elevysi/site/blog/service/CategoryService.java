package com.elevysi.site.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.CategoryDAO;
import com.elevysi.site.blog.entity.Category;

@Service
public class CategoryService extends AbstractServiceImpl<Category, Integer>{
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	public Category findByName(String name){
		return categoryDAO.findByName(name);
	}
}
