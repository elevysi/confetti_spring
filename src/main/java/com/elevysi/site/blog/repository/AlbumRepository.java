package com.elevysi.site.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Upload;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

	@Query(value="SELECT album FROM Album album "
			+ " LEFT JOIN FETCH album.uploads "
			+ " WHERE album.id = :id",
			countQuery = " SELECT COUNT(album) FROM Album album WHERE album.id = :id "	
			)
	Page<Album> findAlbumWithUploads(@Param("id")Integer id, Pageable p);

	@Query(value="SELECT album FROM Album album "
			+ " INNER JOIN album.profileOwner as profileOwner "
			+ " WHERE profileOwner.id =:profileID AND album.linkTable='profiles'",
			countQuery = " SELECT COUNT(album) FROM Album album WHERE profileOwner.id =:profileID AND album.linkTable='profiles'"	
			)
	Page<Album> findProfileAlbums(@Param("profileID")Integer profileID, Pageable constructPageSpecification);
	

	@Query(value="SELECT album from Album album"
			+ " LEFT JOIN FETCH album.profileOwner profile"
			+ "	WHERE album.id =:albumID")
	Album findAlbumWithProfile(@Param("albumID")Integer albumID);

	@Query(value="SELECT album from Album album"
			+ " INNER JOIN FETCH album.profileOwner profile"
			+ "	WHERE album.linkTable = 'profiles'")
	List<Album> findAllProfileAlbums();

	Album findByLinkIdAndLinkTable(Integer linkId, String linkTable);

}
