package com.elevysi.site.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Message;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.repository.ConversationRepository;
import com.elevysi.site.repository.MessageRepository;

@Service
public class ConversationService extends AbstractService{
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	public Conversation findConversationBetween(String firstUsername, String secondUsername) {
		
		return null;
	}
	
	public Conversation findConversationBetween(Profile sender, Profile reciever) {
		return conversationRepository.findProfilesConversation(sender.getId(), reciever.getId());
	}

	public Conversation findByUUID(String conversationUUID) {
		return conversationRepository.findByUuid(conversationUUID);
		
	}

	public Conversation saveConversation(Conversation conversation) {
		Date now = new Date();
		conversation.setCreated(now);
		conversation.setModified(now);
		return conversationRepository.save(conversation);
	}

	

}
