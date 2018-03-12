package com.elevysi.site.blog.repository;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Conversation;
import com.elevysi.site.blog.entity.Message;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface MessageDAO {
	
	public List<Message> searchByTerm(String term);
	public List<Message> getMessages(Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Message getMessage(int id);
	public void deleteMessage(int id);
	public List<Message> getConversationMessages(Conversation conversation, Page page);

}
