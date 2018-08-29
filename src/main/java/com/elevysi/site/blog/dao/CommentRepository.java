package com.elevysi.site.blog.dao;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.commons.dto.ProfileDTO;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	Set<Comment> findByPost(Post post);

//	Set<Comment> findByProfile(ProfileDTO profile);
//	
//	@Query(value="SELECT comment FROM Comment comment "
//			+ "INNER JOIN comment.post as post "
//			+ " LEFT JOIN FETCH comment.profile as profile "
//			+ " LEFT JOIN FETCH profile.profilePicture as profilePicture "
//			+ "WHERE post.id = :postID AND comment.itemType='posts' AND comment.originalComment IS NULL",
//			countQuery=" SELECT COUNT(comment) FROM Comment comment WHERE post.id = :postID AND comment.itemType='posts' AND comment.originalComment IS NULL")
//	Page<Comment> findPostComment(@Param("postID")Integer postID, Pageable p);
//	
//	@Query(value="SELECT comment FROM Comment comment "
//			+ "INNER JOIN comment.play as play "
//			+ " LEFT JOIN FETCH comment.profile as profile "
//			+ " LEFT JOIN FETCH profile.profilePicture as profilePicture "
//			+ "WHERE play.id = :playId AND comment.itemType='plays' AND comment.originalComment IS NULL",
//			countQuery=" SELECT COUNT(comment) FROM Comment comment WHERE play.id = :playId AND  comment.itemType='plays' AND comment.originalComment IS NULL")
//	Page<Comment> findPlayComments(@Param("playId")Integer postID, Pageable p);
//	
//	@Query(value="SELECT comment FROM Comment comment INNER JOIN comment.post"
//			+ " INNER JOIN FETCH comment.post as post"
//			+ " LEFT JOIN FETCH comment.profile as profile ",
//			countQuery = " SELECT COUNT(comment) FROM Comment comment")
//	Page<Comment> findComment(Pageable p);
	
	Comment findById(Integer id);

	Set<Comment> findByItemIdAndItemType(Integer itemId, String itemType);
	
}
