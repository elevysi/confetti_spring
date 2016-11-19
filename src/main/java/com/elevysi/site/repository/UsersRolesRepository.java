package com.elevysi.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.UsersRoles;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Integer>{

}
