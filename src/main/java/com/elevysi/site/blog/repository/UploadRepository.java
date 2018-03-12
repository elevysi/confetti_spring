package com.elevysi.site.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Integer>{

	Upload findById(Integer id);

	List<Upload> findByUuid(String uuid);
	
//	@Query("SELECT upload FROM Upload where upload.profile_id=?1")
//	List<Upload> findByUploadOwnerQ(Integer profile_id);
	
	List<Upload> findByUploadOwner(Profile profile);
	
	Upload findByKeyIdentification(String key_identification);

	Upload findByLinkIdAndLinkTable(Integer linkId, String linkTable);
	
	
	@Query(value="SELECT upload FROM Upload upload "
			+ " INNER JOIN FETCH upload.album album"
			+ " WHERE album.id = :albumId",
			countQuery = " SELECT COUNT(upload) FROM Upload upload WHERE album.id = :albumId "	
			)
	Page<Upload> findAlbumUploads(@Param("albumId")Integer albumId, Pageable p);

	Upload findByAlbum(Album album);

	List<Upload> findByUuidAndDisplay(String uuid, boolean display);
	Upload findOneByUuidAndDisplay(String uuid, boolean display);
	
}
