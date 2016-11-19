package com.elevysi.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.PlayType;

@Repository
public interface PlayTypeRepository extends JpaRepository<PlayType, Integer>{

	PlayType findById(Integer playTypeId);

}
