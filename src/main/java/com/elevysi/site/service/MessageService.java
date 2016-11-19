package com.elevysi.site.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Message;
import com.elevysi.site.repository.MessageRepository;

@Service
public class MessageService extends AbstractService{

	@Autowired
	private MessageRepository messageRepository;
	
	public Message saveMessage(Message message){
		Date now = new Date();
		message.setCreated(now);
		message.setModified(now);
		return messageRepository.save(message);
	}

	public Message updateMessage(Message savedMessage) {
		Date now = new Date();
		savedMessage.setModified(now);
		return messageRepository.save(savedMessage);
	}
	
//	public List<Message> getPaginatedConversationMsgs(Conversation conversation, Integer pageNumber, Integer limit, String sortField, String sortDirection) {
//		
//		List<Message> conversationMsgs = new ArrayList<Message>();
//		
//		Page<Message> requestedPage = messageRepository.findConversationMessages(conversation.getId(), constructPageSpecification(pageNumber -1, limit, sortField, sortDirection));
//		return requestedPage.getContent();
////		conversationMsgs.add(findOne(1));
////		return conversationMsgs;
//	}
	
	public List<Message> findByConversation(Conversation conversation, Integer pageNumber, Integer limit, String sortField, String sortDirection){
		return messageRepository.findByConversation(conversation, constructPageSpecification(pageNumber -1, limit, sortField, sortDirection)).getContent();
	}
	
	public Message findOne(Integer id){
		return messageRepository.findOne(id);
	}
	
}
