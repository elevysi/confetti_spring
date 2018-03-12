package com.elevysi.site.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.UsersRoles;
import com.elevysi.site.blog.repository.UsersRolesRepository;

@Service
public class UsersRolesService {
	
	@Autowired
	private UsersRolesRepository usersRolesRepository;
	
	public void saveUsersRoles(UsersRoles userRole){
		usersRolesRepository.save(userRole);
	}
}
