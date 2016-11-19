package com.elevysi.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elevysi.site.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	User findById(Integer id);

	User findByEmail(String email);

	@Query("SELECT user FROM User user WHERE " +
            "LOWER(user.first_name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(user.last_name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(user.username) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(user.email) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	List<User> searchFor(@Param("searchTerm") String searchTerm);
	

}
