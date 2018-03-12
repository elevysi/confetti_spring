package com.elevysi.site.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.PlayType;

@Repository
public interface PlayTypeRepository extends JpaRepository<PlayType, Integer>{

	PlayType findById(Integer playTypeId);

}
