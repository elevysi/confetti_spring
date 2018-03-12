package com.elevysi.site.blog.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, Integer>{

	@Query(value="SELECT share FROM Share share "
			+ "INNER JOIN share.shareOwner shareOwner "
			+ "WHERE share.itemId =:itemId AND share.itemType =:itemType AND shareOwner.id =:profileID")
	Share findSharedItem(@Param("itemId")Integer itemId, @Param("itemType")String itemType, @Param("profileID")Integer profileID);

	@Query(value="SELECT share FROM Share share "
			+ "LEFT JOIN FETCH share.shareOwner shareOwner "
			+ "WHERE share.itemId =:itemId AND share.itemType =:itemType")
	Set<Share> findItemShares(@Param("itemId")Integer itemId, @Param("itemType")String itemType);

	Set<Share> findByItemIdAndItemType(Integer itemId, String itemType);

}
