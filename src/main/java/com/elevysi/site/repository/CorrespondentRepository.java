package com.elevysi.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Correspondent;

@Repository
public interface CorrespondentRepository extends JpaRepository<Correspondent, Integer>{

}
