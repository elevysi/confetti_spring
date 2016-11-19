package com.elevysi.site.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

//	@Query(value="SELECT message FROM Message message"
//			+ " INNER JOIN FETCH message.conversation conversation"
//			+ " LEFT JOIN FETCH message.profile profile"
//			+ " WHERE conversation.id =:conversationID",
//		countQuery="SELECT message FROM Message message WHERE conversation.id =:conversationID")
//	Page<Message> findConversationMessages(@Param("conversationID")Integer conversationID, Pageable p);
	
	Page<Message> findByConversation(Conversation conversation, Pageable p);
	
	

}
