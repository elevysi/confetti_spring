package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.entity.Conversation;
import com.elevysi.site.blog.entity.Correspondent;
import com.elevysi.site.blog.entity.Message;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.repository.ConversationDAO;
import com.elevysi.site.blog.repository.ConversationRepository;
import com.elevysi.site.blog.repository.MessageRepository;

@Service
public class ConversationService extends AbstractService{
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private ConversationDAO conversationDAO;
	
	@Autowired
	private MessageRepository messageRepository;

	public Conversation findConversationBetween(String firstUsername, String secondUsername) {
		
		return null;
	}
	
	public Conversation findConversationBetween(Profile sender, Profile reciever) {
		return conversationRepository.findProfilesConversation(sender.getId(), reciever.getId());
	}

	public Conversation saveConversation(Conversation conversation) {
		Date now = new Date();
		conversation.setCreated(now);
		conversation.setModified(now);
		return conversationRepository.save(conversation);
	}
	
	public List<Conversation> getConversationsForProfile(Profile profile,  com.elevysi.site.blog.pojo.Page page){
		return conversationDAO.getConversationsForProfile(profile, page);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return conversationDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}

	public Conversation saveNewConversationMessage(Conversation conversation, Message message, Set<Profile> involvedProfiles){
		
		Date now = new Date();
		conversation.setCreated(now);
		conversation.setModified(now);
		
		message.setCreated(now);
		message.setModified(now);
		
		for(Profile profile : involvedProfiles){
			if(! conversation.isConversationContainsProfile(profile)){
				Correspondent correspondent = new Correspondent();
				correspondent.setCreated(now);
				correspondent.setModified(now);
				correspondent.setOwningCorrespondentProfile(profile);
				conversation.addCorrespondent(correspondent);
			}
		}
		
		conversation.addMessage(message);
		
		return conversationRepository.save(conversation);
	}
	
	public Conversation addConversationMessage(Conversation conversation, Message message, Set<Profile> involvedProfiles){
		
		Date now = new Date();
		conversation.setModified(now);
		
		message.setCreated(now);
		message.setModified(now);
		
		for(Profile profile : involvedProfiles){
			
			if(! conversation.isConversationContainsProfile(profile)){
				Correspondent correspondent = new Correspondent();
				correspondent.setCreated(now);
				correspondent.setModified(now);
				correspondent.setOwningCorrespondentProfile(profile);
				conversation.addCorrespondent(correspondent);
			}
		}
		conversation.addMessage(message);
		
		return conversationRepository.save(conversation);
	}
	
	public Conversation addMessageToConversation(Conversation conversation, String msgText, Profile profile){
		
		Date now = new Date();
		conversation.setModified(now);
		
		Message message = new Message();
		message.setCreated(now);
		message.setModified(now);
		message.setMessage(msgText);
		message.setProfile(profile);
		
		conversation.addMessage(message);
		
		return conversationRepository.save(conversation);
	}
	
	public Conversation getCorrespondentsConversation(Set<Correspondent> correspondents){
		
		Set<Profile> profiles = new HashSet<Profile>();
		for(Correspondent correspondent : correspondents){
			profiles.add(correspondent.getOwningCorrespondentProfile());
		}
		return conversationDAO.getProfilesConversation(profiles);
	}
	
	public Conversation getProfilesConversation(Set<Profile> profiles){
		return conversationDAO.getProfilesConversation(profiles);
	}
	
	public Conversation getConversationByUUID(String conversationUUID) {
		return conversationDAO.getConversationByUUID(conversationUUID);
	}

}
