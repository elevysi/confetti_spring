package com.elevysi.site.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
