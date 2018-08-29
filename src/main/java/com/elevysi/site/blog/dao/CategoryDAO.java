package com.elevysi.site.blog.dao;


import com.elevysi.site.blog.entity.Category;

public interface CategoryDAO extends AbstractDAO<Category, Integer> {

	public Category findByName(String name);
	
}
