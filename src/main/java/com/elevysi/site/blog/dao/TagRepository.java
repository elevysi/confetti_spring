package com.elevysi.site.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elevysi.site.blog.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{

}
