package com.elevysi.site.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.MediaKind;

@Repository
public interface MediaKindRepository extends JpaRepository<MediaKind, Integer>{

}
