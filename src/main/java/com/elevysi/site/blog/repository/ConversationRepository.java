package com.elevysi.site.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.blog.entity.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer>{

	@Query(value="SELECT conversation FROM Conversation conversation"
			+ " INNER JOIN FETCH conversation.correspondents correspondent"
			+ " INNER JOIN FETCH correspondent.owningCorrespondentProfile profile"
			+ " WHERE profile.id =:profileID1 OR profile.id =:profileID2")
	Conversation findProfilesConversation(@Param("profileID1")Integer profileID1, @Param("profileID2")Integer profileID2);

}
