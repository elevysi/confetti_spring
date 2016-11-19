package com.elevysi.site.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.ProfileType;
import com.elevysi.site.entity.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Profile findByUserAndProfileType(User user, ProfileType profileType);

	List<Profile> findByUser(User user);
	
	@Query("SELECT profile FROM Profile profile INNER JOIN profile.reverse_friends reverse  where reverse.id=?1")
//	@Query(value = "SELECT * FROM profiles p  INNER JOIN profile_friends pf on p.id = pf.friend_id  INNER JOIN profiles f on pf.profile_id = f.id where f.id=?1",
//            nativeQuery = true
//    )
	Set<Profile> findProfileFriends(int id);
	
	
	@Query("SELECT profile FROM Profile profile INNER JOIN profile.friends friend  where friend.id=?1")
	Set<Profile> findInverseProfileFriends(int id);

	Profile findById(Integer id);
	
	Profile findByName(String name);

	

	
}
