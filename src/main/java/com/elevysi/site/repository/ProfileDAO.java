package com.elevysi.site.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.ProfileType;
import com.elevysi.site.entity.User;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

public interface ProfileDAO {
	
	public List<Profile> getProfiles(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Profile update(Profile profile);
	public Profile findById(int id);
	public Profile findProfileByUser(User user);
	public Profile getUserPrincipalProfile(User user);
	public List<Profile> findFollowing(int id);
	public List<Profile> findFollowers(int id);
	public Profile findByUserAndProfileType(User user, ProfileType profileType);
	public Profile findByName(String name);
	


}
