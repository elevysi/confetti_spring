package com.elevysi.site.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Like;



@Repository
public interface LikeRepository extends JpaRepository <Like, Integer>{

	@Query(value="SELECT profileLike FROM Like profileLike "
				+ "INNER JOIN profileLike.likeOwner likeOwner "
				+ "WHERE profileLike.itemId =:itemID AND profileLike.itemType =:itemType AND likeOwner.id =:profileID")
	Like findLikedItem(@Param("itemID")Integer itemID, @Param("itemType")String itemType, @Param("profileID")Integer profileID);

	Set<Like> findByItemIdAndItemType(Integer itemId, String itemType);
	
	@Query(value="SELECT itemLike FROM Like itemLike "
			+ "LEFT JOIN FETCH itemLike.likeOwner likeOwner "
			+ "WHERE itemLike.itemId =:itemId AND itemLike.itemType =:itemType")
	Set<Like> findItemLikes(@Param("itemId")Integer itemId, @Param("itemType")String itemType);

}
