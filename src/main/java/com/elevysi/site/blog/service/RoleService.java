package com.elevysi.site.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Role;
import com.elevysi.site.blog.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	public Role findOne(int id) {
		return roleRepository.findOne(id);
		
	}

}
