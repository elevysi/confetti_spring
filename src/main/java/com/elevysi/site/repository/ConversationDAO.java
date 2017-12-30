package com.elevysi.site.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;

import com.elevysi.site.entity.Conversation;
import com.elevysi.site.entity.Correspondent;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;

public interface ConversationDAO {
	
	public Conversation searchFor(String term);
	public List<Conversation> getConversationsForProfile(Profile profile, Page page);
	public long getCount();
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection);
	public Conversation getCorrespondentsConversation(Set<Correspondent> correspondents);
	public Conversation getConversationByUUID(String uuid);
	public Conversation getProfilesConversation(Set<Profile> profiles);
}
