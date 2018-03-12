package com.elevysi.site.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.ProfileType;

@Repository
public interface ProfileTypeRepository extends JpaRepository<ProfileType, Integer>{

	ProfileType findByName(String name);
	List<ProfileType> findAll();

}
