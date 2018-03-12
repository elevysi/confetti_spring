package com.elevysi.site.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Correspondent;

@Repository
public interface CorrespondentRepository extends JpaRepository<Correspondent, Integer>{

}
