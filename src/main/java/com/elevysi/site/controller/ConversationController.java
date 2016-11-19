package com.elevysi.site.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Correspondent;
import com.elevysi.site.entity.Message;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.MessageDisplay;
import com.elevysi.site.pojo.ReturnValue;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.security.ActiveUser;
import com.elevysi.site.service.ConversationService;
import com.elevysi.site.service.CorrespondentService;
import com.elevysi.site.service.MessageService;
import com.elevysi.site.service.ProfileService;

@Controller
@RequestMapping(value="/conversations")
public class ConversationController extends AbstractController{
	
	private static final int NO_CONVERSATION_MSGS = 30;
	
	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private CorrespondentService correspondentService;
	
	@Autowired
	private ProfileService profileService;

	@RequestMapping(value="/conversate")
	public String conversate(Model model){
		
		
		
		
		return "conversate";
	}
	
	@RequestMapping(value="/conversate/{username}")
	public String conversateWith(@PathVariable("username")String username, Model model, RedirectAttributes redirectAttributes){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
		
		Profile actingProfile = activeUser.getActiveProfile();
		
		
		Profile reciever = profileService.findOne(username);
		
		if(reciever == null){
			SessionMessage sessionMessage = new SessionMessage("Could not find the specified reciever!.");
			sessionMessage.setDangerClass();
			redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
			return "redirect:/profile/";
		}
		
		Conversation profilesConversation = conversationService.findConversationBetween(actingProfile, reciever);
		
		if(profilesConversation != null){
			model.addAttribute("conversation", profilesConversation);
		}else{
			Conversation conversion = new Conversation();
			String convUuid = conversion.generateUUID();
			conversion.setUuid(convUuid);
			model.addAttribute("conversation", conversion);
		}
		
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("reciever", reciever);
		return "conversateWith";
	}
	
	@RequestMapping(value="/{recieverUsername}/{conversationUUID}", method=RequestMethod.GET)
	public @ResponseBody List<MessageDisplay> getPreviousMessages(@PathVariable("recieverUsername")String recieverUsername, @PathVariable("conversationUUID")String conversationUUID){
		
		List<MessageDisplay> previousMsgs = new ArrayList<MessageDisplay>();
		Conversation conversation = conversationService.findByUUID(conversationUUID);
		if(conversation != null){
			 //Get Paginated Conversation Msgs here
			
//			Set<Message> convMsgs = messageService.findByConversation(conversation, 1, NO_CONVERSATION_MSGS, SORT_FIELD, SORT_DIRECTION);
//			Iterator<Message> iterator = convMsgs.iterator();
//			while(iterator.hasNext()){
//				 Message ithMsg = iterator.next();
//				 MessageDisplay msgD = new MessageDisplay(ithMsg.getId(), ithMsg.getProfile().getName(), ithMsg.getMessage());
//				 previousMsgs.add(msgD);
//			}
			
			
			 List<Message> convMsgs = messageService.findByConversation(conversation, 1, NO_CONVERSATION_MSGS, SORT_FIELD, SORT_DIRECTION);
			 if(convMsgs == null){
				 return previousMsgs;
			 }
			 
			 for(int i =0; i < convMsgs.size(); i++){
				 Message ithMsg = convMsgs.get(i);
				 MessageDisplay msgD = new MessageDisplay(ithMsg.getId(), ithMsg.getProfile().getName(), ithMsg.getMessage());
				 previousMsgs.add(msgD);
			 }
			
//			 List<Message> convMsgs = messageService.getPaginatedConversationMsgs(conversation, 1, NO_CONVERSATION_MSGS, SORT_FIELD, SORT_DIRECTION);
//			 if(convMsgs == null){
//				 return previousMsgs;
//			 }
//			 
//			 for(int i =0; i < convMsgs.size(); i++){
//				 Message ithMsg = convMsgs.get(i);
//				 MessageDisplay msgD = new MessageDisplay(ithMsg.getId(), ithMsg.getProfile().getName(), ithMsg.getMessage());
//				 previousMsgs.add(msgD);
//			 }
		}else{
			//Test Data
			
			MessageDisplay msg1 = new MessageDisplay(1, "Elvis", "I salute you");
			previousMsgs.add(msg1);
			
			MessageDisplay msg2 = new MessageDisplay(2, "Tony", "Hey");
			previousMsgs.add(msg2);
			
			MessageDisplay msg3 = new MessageDisplay(3, "Elvis", "How are you doing?");
			previousMsgs.add(msg3);
		}
		
		return previousMsgs;
		
	}
	
	@RequestMapping(value="/{recieverUsername}/{conversationUUID}", method=RequestMethod.POST)
	public @ResponseBody ReturnValue doAddMessage(@PathVariable("conversationUUID")String conversationUUID, @PathVariable("recieverUsername")String recieverUsername, @RequestParam("msg")String msg){
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed");
		
		/**
		 * Find the recipient profile
		 */
		Profile recieverProfile = profileService.findOne(recieverUsername);
		
		
		if(recieverProfile == null){
			return returnValue;
		}
		
		
		
		
		/**
		 * Find the logged in Profile
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
		Profile actingProfile = activeUser.getUserProfile();
		
		
		Conversation conversation = conversationService.findByUUID(conversationUUID);
		Conversation savedConversation;
		
		boolean newConversation = true;
		if(conversation != null){
			newConversation = false;
			
			savedConversation = conversation;
		}else{
			/**
			 * Create Conversation
			 */
			Conversation convCreate = new Conversation();
			convCreate.setUuid(conversationUUID);
			savedConversation = conversationService.saveConversation(convCreate);
			
			
		}
		
		
		
		if(savedConversation != null){
			
			
			
			/**
			 * Find Saved Conversation with Correspondent and Messages
			 */
			
			
			/**
			 * Add Correspondents to this conversation
			 */
			if(newConversation){
				
				Correspondent reciever = new Correspondent();
				reciever.setOwningCorrespondentProfile(recieverProfile);
				reciever.setConversation(savedConversation);
				Correspondent savedReciever = correspondentService.saveCorrespondent(reciever);
				
				
				/**
				 * Create the correspondent
				 */
				Correspondent sender = new Correspondent();
				sender.setOwningCorrespondentProfile(actingProfile);
				sender.setConversation(savedConversation);
				Correspondent savedSender = correspondentService.saveCorrespondent(sender);
				
				savedConversation.addCorrespondent(savedSender);
				savedConversation.addCorrespondent(savedReciever);
				
			}
			
			/**
			 * Add messages to this conversation
			 */
			
			Message message = new Message();
			message.setMessage(msg);
			message.setProfile(actingProfile);
			message.setConversation(savedConversation);
			Message savedMessage = messageService.saveMessage(message);
			
//			savedConversation.addMessage(savedMessage);
			conversationService.saveConversation(savedConversation);
			
			returnValue.setCode(1);
			returnValue.setMessage("Success");
			
			
		}
		
		return returnValue;
		
		
	}
}
