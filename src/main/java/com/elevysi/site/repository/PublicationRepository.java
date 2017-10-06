package com.elevysi.site.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.elevysi.site.entity.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer>{

	
	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN publication.play as play"
			+ " WHERE play.id =:playID")
	Publication findPlayPublication(@Param("playID")Integer playID);

	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN publication.post as post"
			+ " WHERE post.id =:postID")
	Publication findPostPublication(@Param("postID")Integer postID);

	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN publication.album as album"
			+ " WHERE album.id =:albumID")
	Publication findAlbumPublication(@Param("albumID")Integer albumID);
	
	
	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN publication.profilePublication as profilePublication"
			+ " WHERE profilePublication.id =:profileID")
	Publication findProfilePublication(@Param("profileID")Integer profileID);
	
	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN publication.dossier as dossier"
			+ " WHERE dossier.id =:dossierID")
	Publication findDossierPublication(@Param("dossierID")Integer dossierID);
	
	
	@Query(value="SELECT publication FROM Publication publication"
			+ " INNER JOIN FETCH publication.profile profile"
			+ " LEFT JOIN FETCH profile.posts posts"
			+ " LEFT JOIN FETCH profile.plays plays"
			+ " LEFT JOIN FETCH profile.albums albums"
			+ " WHERE profile.id =:profileID",
			countQuery="SELECT COUNT(publication) FROM Publication publication WHERE profile.id =:profileID") 
	Page<Publication> findProfilePublications(@Param("profileID")Integer profileID, Pageable constructPageSpecification);

}
