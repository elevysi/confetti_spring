package com.elevysi.site.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Message;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

public interface MessageDAO {
	
	public List<Message> searchByTerm(String term);
	public List<Message> getMessages(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Message getMessage(int id);
	public void deleteMessage(int id);
	public List<Message> getConversationMessages(Conversation conversation, Page page);

}
