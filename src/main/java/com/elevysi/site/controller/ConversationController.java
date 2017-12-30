package com.elevysi.site.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Conversation_;
import com.elevysi.site.entity.Correspondent;
import com.elevysi.site.entity.Message;
import com.elevysi.site.entity.Message_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.MessageDisplay;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.ReturnValue;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.pojo.Page.SortDirection;
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
	public String conversate(
			Model model
	){
		
		
		return "conversate";
	}
	
	@RequestMapping(value={"/messages/", "/messages/*"})
	public String conversations(
			Model model,
			@ModelAttribute("sessionMessage") SessionMessage sessionMessage, 
			@RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber
	){
		
		Profile activeProfile = conversationService.getActiveProfile();
		com.elevysi.site.pojo.Page page = conversationService.buildOffsetPage(pageNumber, DEFAULT_NO_ITEMS, Conversation_.modified, SortDirection.DESC);
		List<Conversation> conversations = conversationService.getConversationsForProfile(activeProfile, page);
		
		model.addAttribute("activeProfile", activeProfile);
		model.addAttribute("profile", activeProfile);
		model.addAttribute("conversations", conversations);
		
		return "conversationIndex";
	}
	
	@RequestMapping(value={"/add/", "/add"})
	public String add(
			Model model,
			@RequestParam(value="uuid", defaultValue="", required=false)String uuid,
			@ModelAttribute("sessionMessage") SessionMessage sessionMessage,
			final RedirectAttributes redirectAttributes
	){
		
		if(! uuid.equals("")){
			Conversation conversation = conversationService.getConversationByUUID(uuid);
			if(conversation == null){
				//Generate new UUID
				uuid = Conversation.generateStaticUUID();
			}
		}else{
			uuid = Conversation.generateStaticUUID();
		}
	
		Profile actingProfile = conversationService.getActiveProfile();
		
		Message message = new Message();
		
		model.addAttribute("conversationUUID", uuid);
		model.addAttribute("profile", actingProfile);
		model.addAttribute("message", message);
		model.addAttribute("sessionMessage", sessionMessage);
		return "conversationAdd";
	}
	
	@RequestMapping(value={"/add/", "/add"}, method=RequestMethod.POST)
	public String doAdd(
			Model model,
			@Valid @ModelAttribute("message") Message message,
			BindingResult result,
			@RequestParam(value="correspondentsInput", defaultValue="") String[] profileIDs,
			@RequestParam("conversationUUID") String conversationUUID,
			final RedirectAttributes redirectAttributes
	){
		
		SessionMessage sessionMessage = new SessionMessage();
		Profile actingProfile = conversationService.getActiveProfile();
		
		if(! result.hasErrors()){
				
			//Search and see if this conversation exists already
			Set<Profile> involvedProfiles = new HashSet<Profile>();
			
			
			if(! profileIDs.equals("")){
				for(String profileID : profileIDs){
					Profile correspondentProfile = profileService.findOne(Integer.parseInt(profileID));
					involvedProfiles.add(correspondentProfile);
				}
			}
			
			if(! involvedProfiles.contains(actingProfile)){
				involvedProfiles.add(actingProfile);
			}
			
			message.setProfile(actingProfile);
			
			/**
			 * Check if this can be found from the UUID
			 * Second Check with the correspondents i.e. 
			 * conversationService.getProfilesConversation(involvedProfiles);
			 */
			Conversation existingConversation;
	//		
			existingConversation = conversationService.getConversationByUUID(conversationUUID);
	//		if(false){
			if(existingConversation != null){
				//Add messages to this conversation
				conversationService.addConversationMessage(existingConversation, message, involvedProfiles);
				sessionMessage.setMsgText("Your message was successfully sent");
				sessionMessage.setSuccessClass();
				redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
				return "redirect:/conversations/view/"+existingConversation.getUuid();
			}else{
				
				
				if(involvedProfiles.size() < 2){
					sessionMessage = new SessionMessage("The conversation must involve at least one other profile.");
					sessionMessage.setDangerClass();
					redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
				}else{
					
					//Check if no conversation of these Profiles exists already
					existingConversation = conversationService.getProfilesConversation(involvedProfiles);
					if(existingConversation != null){
						conversationService.addConversationMessage(existingConversation, message, involvedProfiles);
						sessionMessage.setMsgText("Your message was successfully sent");
						sessionMessage.setSuccessClass();
						redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
						return "redirect:/conversations/view/"+existingConversation.getUuid();
					}else{
						
						Conversation conversation = new Conversation();
						conversation.setUuid(conversationUUID);
						
						Conversation savedConversation = conversationService.saveNewConversationMessage(conversation, message, involvedProfiles);
						if(savedConversation != null){
							sessionMessage.setMsgText("Your message was successfully sent");
							sessionMessage.setSuccessClass();
							redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
							return "redirect:/conversations/view/"+savedConversation.getUuid();
						}
					}
					
					
				}
			}
			
		}else{
			sessionMessage.setMsgText("Please check if the form was correctly filled in!.");
			sessionMessage.setDangerClass();
		}
		
		model.addAttribute("profile", actingProfile);
		model.addAttribute("message", message);
		model.addAttribute("conversationUUID", conversationUUID);
		model.addAttribute("sessionMessage", sessionMessage);
		
		return "conversationAdd";
	}
	
	@RequestMapping(value={"/view/{conversationUUID}", "/view/{conversationUUID}/"}, method=RequestMethod.GET)
	public String viewConversation(
			Model model,
			@PathVariable("conversationUUID") String conversationUUID,
			@ModelAttribute("sessionMessage") SessionMessage sessionMessage,
			final RedirectAttributes redirectAttributes
	){
		//Find the conversation
		Conversation conversation = conversationService.getConversationByUUID(conversationUUID);
		Profile actingProfile = conversationService.getActiveProfile();
		
		if(conversation == null){
			Conversation conversion = new Conversation();
			String convUuid = conversion.generateUUID();
			conversion.setUuid(convUuid);
		}
		
		model.addAttribute("profile", actingProfile);
		model.addAttribute("conversation", conversation);
		model.addAttribute("sessionMessage", sessionMessage);
		
		return "conversationView";
	}
	
	@RequestMapping(value="/message/{username}")
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
	
	@RequestMapping(value="/messagesAjax/{conversationUUID}", method=RequestMethod.GET)
	public @ResponseBody List<MessageDisplay> getPreviousMessagesAjax(
			@PathVariable("conversationUUID")String conversationUUID,
			@RequestParam(value="page", defaultValue="1", required=false)int pageIndex
	){
		
		List<MessageDisplay> previousMsgs = new ArrayList<MessageDisplay>();
		Conversation conversation = conversationService.getConversationByUUID(conversationUUID);
		if(conversation != null){
			
			OffsetPage page = messageService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Message_.created, SortDirection.ASC);
			List<Message> convMsgs = messageService.getConversationMessages(conversation, page);
			 
			 if(convMsgs == null){
				 return previousMsgs;
			 }
			 
			 for(int i =0; i < convMsgs.size(); i++){
				 Message ithMsg = convMsgs.get(i);
				 //Check if it has an avatar
				 String avatarString = "";
				 if(ithMsg.getProfile().getProfilePicture().iterator().hasNext()){
					 avatarString = ithMsg.getProfile().getProfilePicture().iterator().next().getKeyIdentification();
				 }
				 MessageDisplay msgD = new MessageDisplay(ithMsg.getId(), ithMsg.getProfile().getName(), ithMsg.getMessage(), avatarString);
				 previousMsgs.add(msgD);
			 }
		}
		
		return previousMsgs;
		
	}
	
	@RequestMapping(value="/messagesAjax/{conversationUUID}", method=RequestMethod.POST)
	public @ResponseBody ReturnValue doAddMessageAjax(
			@PathVariable("conversationUUID")String conversationUUID, 
			@RequestParam("msg")String msg
	){
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed");
		
		Conversation conversation = conversationService.getConversationByUUID(conversationUUID);
		Profile actingProfile = conversationService.getActiveProfile();
		if(conversation != null){
			conversationService.addMessageToConversation(conversation, msg, actingProfile);
			returnValue.setCode(1);
			returnValue.setMessage("Success");
		}
		
		return returnValue;
		
		
	}
}
