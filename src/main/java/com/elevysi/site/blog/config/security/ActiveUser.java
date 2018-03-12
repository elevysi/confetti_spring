package com.elevysi.site.blog.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.User;

import com.elevysi.site.blog.entity.Profile;

public class ActiveUser extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8778094825710419801L;
	private List<Profile> profiles;
	
	private Profile userProfile;
	
	private String first_name;
	
	private Profile activeProfile;
	
	

	public Profile getActiveProfile() {
		return activeProfile;
	}

	public void setActiveProfile(Profile activeProfile) {
		this.activeProfile = activeProfile;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}

	public ActiveUser(String username, String password, boolean enabled,
	         boolean accountNonExpired, boolean credentialsNonExpired,
	         boolean accountNonLocked,
	         Collection authorities,
	         String first_name,
	         Profile userProfile,
	         Profile activeProfile,
	         List<Profile> profiles
			) {

	             super(username, password, enabled, accountNonExpired,
	                credentialsNonExpired, accountNonLocked, authorities);
	             
	             this.userProfile = userProfile;
	             this.activeProfile = activeProfile;
	             this.profiles = profiles;
	             this.first_name = first_name;
     }
	
	
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

}
