package com.elevysi.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findById(Integer category_id);

}
