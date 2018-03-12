package com.elevysi.site.blog.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.blog.entity.Conversation;
import com.elevysi.site.blog.entity.Correspondent;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.Page.SortDirection;

public interface ConversationDAO {
	
	public Conversation searchFor(String term);
	public List<Conversation> getConversationsForProfile(Profile profile, Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Conversation getCorrespondentsConversation(Set<Correspondent> correspondents);
	public Conversation getConversationByUUID(String uuid);
	public Conversation getProfilesConversation(Set<Profile> profiles);
}
