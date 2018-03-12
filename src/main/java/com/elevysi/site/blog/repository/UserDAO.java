package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.security.access.prepost.PreAuthorize;

import com.elevysi.site.blog.entity.User;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface UserDAO {
	public List<User> searchByTerm(String term);
	public List<User> getUsers(Page page);
	public User findById(int id);
	public User loadByUsername(String username);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public User update(User user);
	public User findByUsername(String username);
}
