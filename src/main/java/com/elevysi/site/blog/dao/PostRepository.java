package com.elevysi.site.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.commons.dto.ProfileDTO;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post>{
	
	

//	List<Post> findByProfile(ProfileDTO profile);
//
//	Page<Post> findByProfile(ProfileDTO profile, Pageable constructPageSpecification);
	
	
//	@Query("SELECT post FROM Post post "
//			+ " LEFT JOIN FETCH post.categories postCategories "
//			+ " LEFT JOIN FETCH post.uploads as uploads "
//			+ " LEFT JOIN FETCH post.profile profile "
//			+ " WHERE post.id = :id ")
//	Post loadFullPost(@Param("id")Integer id);
	
	/**
	 * JPQL with Pageable
	 * http://forum.spring.io/forum/spring-projects/data/126415-is-it-possible-to-use-query-and-pageable
	 * http://stackoverflow.com/questions/22345081/spring-data-jpa-query-and-pageable
	 * 
	 * pageable query specified join fetching, but the owner of the fetched association was not present in the select list
	 * 
	 * http://stackoverflow.com/questions/21549480/spring-data-fetch-join-with-paging-is-not-working
	 * 
	 * The easiest way is to use the countQuery attribute of the the @Query annotation to provide a custom query to be used.
	 * 
	 * @param constructPageSpecification
	 * @return
	 */
	
//	@Query(value="SELECT post FROM Post post "
//			+ " LEFT JOIN FETCH post.profile as postProfile "
//			+ " LEFT JOIN FETCH post.publication as postPublication "
//			+ " LEFT JOIN FETCH post.categories postCategories "
//			+ " LEFT JOIN FETCH post.uploads as uploads ",
//			countQuery = " SELECT COUNT(post) FROM Post post "	
//			)
//	Page<Post> findAllLatest(Pageable constructPageSpecification);
	
//	@Query(value="SELECT post FROM Post post "
//			+ " INNER JOIN FETCH post.profile as profile "
//			+ " LEFT JOIN FETCH post.categories postCategories "
//			+ " LEFT JOIN FETCH post.uploads as uploads "
//			+ " WHERE profile.id = :profileID",
//			countQuery = " SELECT COUNT(post) FROM Post post WHERE profile.id = :profileID  "	
//			)
//	Page<Post> findAllLatestForProfile(@Param("profileID")Integer profileID, Pageable constructPageSpecification);
	
//	@Query(value="SELECT post FROM Post post "
//			+ " INNER JOIN FETCH post.profile as profile "
//			+ " LEFT JOIN FETCH post.categories postCategories "
//			+ " LEFT JOIN FETCH post.publication postPublication "
//			+ " LEFT JOIN FETCH post.uploads as uploads "
//			+ " WHERE profile.id = :profileID AND post.id != :viewedPostID ",
//			countQuery = " SELECT COUNT(post) FROM Post post WHERE profile.id = :profileID AND post.id != :viewedPostID "	
//			)
//	Page<Post> findAllLatestForProfileExcept(@Param("profileID")Integer profileID, @Param("viewedPostID")Integer viewedPostID, Pageable constructPageSpecification);
	
//	@Query(value="SELECT post FROM Post post "
//			+ " INNER JOIN post.profile as profile "
//			+ " LEFT JOIN FETCH post.categories postCategories "
//			+ " LEFT JOIN FETCH post.uploads as uploads "
//			+ " LEFT JOIN FETCH post.comments "
//			+ " WHERE profile.id = :profileID ",
//			countQuery = " SELECT COUNT(post) FROM Post post WHERE profile.id = :profileID"	
//			)
//	Page<Post> findProfilePosts(@Param("profileID")Integer profileID, Pageable constructPageSpecification);
//
//
//	@Query(value="SELECT post FROM Post post"
//			+ " LEFT JOIN FETCH post.profile as profile",
//			countQuery="SELECT post FROM Post post")
//	Page<Post> findAllWithOwner(Pageable constructPageSpecification);


	
}
