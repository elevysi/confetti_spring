package com.elevysi.site.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Play;

@Repository
public interface PlayRepository extends JpaRepository<Play, Integer>{

	@Query(value="SELECT play FROM Play play"
			+ " INNER JOIN play.playType as playType"
			+ " LEFT JOIN FETCH play.playProfile as playProfile"
			+ " WHERE playType.id =:typeID AND play.featured=1",
			countQuery = " SELECT COUNT(play) FROM Play play WHERE playType.id =:typeID AND play.featured=1")
	Page<Play> findFeaturedPlaywithType(@Param("typeID") Integer typeID, Pageable p);

	@Query(value="SELECT play from Play play"
			+ " LEFT JOIN FETCH play.playProfile as playProfile"
			+ " WHERE play.id =:playID")
	Play loadViewPlay(@Param("playID")Integer playID);

	@Query(value="SELECT play from Play play"
			+ " LEFT JOIN FETCH play.playType as playType"
			+ " INNER JOIN play.playProfile as playProfile"
			+ " WHERE playProfile.id =:profileId AND play.id !=:playId", 
			countQuery = "SELECT COUNT(play) FROM Play play WHERE playProfile.id =:profileId AND play.id !=:playId")
	Page<Play> findAllLatestForProfileExcept(@Param("profileId")Integer profileId, @Param("playId")Integer playId, Pageable constructPageSpecification);
	
	@Query(value="SELECT play from Play play"
			+ " LEFT JOIN FETCH play.playType as playType"
			+ " INNER JOIN play.playProfile as playProfile"
			+ " WHERE playProfile.id =:profileId", 
			countQuery = "SELECT COUNT(play) FROM Play play WHERE playProfile.id =:profileId")
	Page<Play> findAllLatestForProfile(@Param("profileId")Integer profileId, Pageable constructPageSpecification);

	@Query(value="SELECT play FROM Play play"
			+ " LEFT JOIN FETCH play.playProfile profile"
			+ " GROUP BY play.id")
	List<Play> findAllPlaysWithProfile();
}
