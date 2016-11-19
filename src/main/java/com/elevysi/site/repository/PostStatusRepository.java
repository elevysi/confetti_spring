package com.elevysi.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.PostStatus;

@Repository
public interface PostStatusRepository extends JpaRepository<PostStatus, Integer> {

	PostStatus findById(Integer id);

}
