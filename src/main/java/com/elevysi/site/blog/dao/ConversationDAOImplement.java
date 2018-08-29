package com.elevysi.site.blog.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.Conversation;
import com.elevysi.site.blog.entity.Correspondent;
import com.elevysi.site.blog.entity.Message;
import com.elevysi.site.commons.pojo.OffsetPage;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@Repository
@Transactional
public class ConversationDAOImplement implements ConversationDAO {
	
//	@PersistenceContext
//	private EntityManager em;
//
//	public Conversation searchFor(String term){
//		return null;
//	}
//	/**
//	 * Insipiration
//	 * https://stackoverflow.com/questions/8135612/jpa-criteria-for-many-to-many-relationship
//	 * https://www.programcreek.com/java-api-examples/index.php?api=javax.persistence.criteria.SetJoin
//	 */
//	public List<Conversation> getConversationsForProfile(Profile profile, Page page){
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Conversation> criteria = cb.createQuery(Conversation.class);
//		Root<Conversation> conversationRoot = criteria.from(Conversation.class);
//		
////		CriteriaQuery<Correspondent> criteriaQuery = cb.createQuery(Correspondent.class);
////		Root<Correspondent> correspondentRoot = criteriaQuery.from(Correspondent.class);
////		criteriaQuery.where(cb.equal(correspondentRoot.get(Correspondent_.owningCorrespondentProfile),  profile));
//		
//		SetJoin<Conversation, Correspondent> conversationsJoin = conversationRoot.join(Conversation_.correspondents);
//		criteria.select(conversationRoot).where(cb.equal( conversationsJoin.get( Correspondent_.owningCorrespondentProfile ), profile));
//		
//		TypedQuery<Conversation> query = page.createQuery(em, criteria, conversationRoot);
//		List<Conversation> conversations =  query.getResultList();
//		
//		for(Conversation conversation : conversations){
//			Hibernate.initialize(conversation.getConversationMessages());
//			Hibernate.initialize(conversation.getCorrespondents());
//			
//			for(Correspondent correspondent: conversation.getCorrespondents()){
//				Hibernate.initialize(correspondent.getOwningCorrespondentProfile());
//				Hibernate.initialize(correspondent.getOwningCorrespondentProfile().getProfilePicture());
//			}
//			
//		}
//		return conversations;
//		
//	}
//	public long getCount(){
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//		cq.select(cb.count(cq.from(Conversation.class)));
//		
//		return em.createQuery(cq).getSingleResult();
//	}
//	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
//		return new OffsetPage(pageIndex, size, getCount(), sortField, sortDirection, Conversation_.modified, Conversation_.created, Conversation_.id);
//	}
//	
//	/**
//	 * IN OPERATOR
//	 * https://stackoverflow.com/questions/42530677/jpa-criteria-builder-in-clause-query
//	 * https://stackoverflow.com/questions/9321916/jpa-criteriabuilder-how-to-use-in-comparison-operator
//	 * https://stackoverflow.com/questions/17154676/criteria-jpa-2-with-3-tables
//	 */
//	public Conversation getCorrespondentsConversationTrials(Set<Correspondent> correspondents){
//		
//		List<Profile> involvedProfiles = new ArrayList<Profile>();
//		for(Correspondent correspondent : correspondents){
//			involvedProfiles.add(correspondent.getOwningCorrespondentProfile());
//		}
//		
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Conversation> query = builder.createQuery(Conversation.class);
//		Root<Conversation> fromConversation = query.from(Conversation.class);
//		
//		SetJoin<Conversation, Correspondent> correspondentsJoin = fromConversation.join(Conversation_.correspondents, JoinType.INNER);
//		Join<Correspondent, Profile> profilesJoin = correspondentsJoin.join("owningCorrespondentProfile", JoinType.INNER);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		
////		Expression<Profile> correspondentExpression = profilesJoin.get("owningCorrespondentProfile");
//
//		//		conditions.add(builder.equal(profilesJoin.get(Profile_.id), 1));
//		conditions.add(profilesJoin.in(involvedProfiles));
////		conditions.add(correspondentExpression.in(involvedProfiles));
//		
//		TypedQuery<Conversation> typedQuery = em.createQuery(query
//		        .select(fromConversation)
//		        .where(conditions.toArray(new Predicate[] {}))
////		        .orderBy(builder.desc(fromConversation.get("created")))
//		        .distinct(true)
//		);
//		
//		List<Conversation>conversations = typedQuery.getResultList();
//		Conversation conversation = null;
//		if(! conversations.isEmpty()){
//			conversation = conversations.get(0);
//			Hibernate.initialize(conversation.getConversationMessages());
////			Hibernate.initialize(conversation.getConversationMessages());
//		}
//		
//		return conversation;
//	}
//	
//	public Conversation getCorrespondentsConversation(Set<Correspondent> correspondents){
//		return null;
//	}
//	
//	public Conversation getProfilesConversation(Set<Profile> profiles){
//		
//		List<Integer> profileIds = new ArrayList<Integer>();
//		for(Profile profile : profiles){
//			profileIds.add(profile.getId());
//		}
//		
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Conversation> query = builder.createQuery(Conversation.class);
//		Root<Conversation> fromConversation = query.from(Conversation.class);
//		
//		SetJoin<Conversation, Correspondent> correspondentsJoin = fromConversation.join(Conversation_.correspondents, JoinType.INNER);
//		Join<Correspondent, Profile> profilesJoin = correspondentsJoin.join(Correspondent_.owningCorrespondentProfile, JoinType.INNER);
//		
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		conditions.add(profilesJoin.get(Profile_.id).in(profileIds));
//		
//		
//		TypedQuery<Conversation> typedQuery = em.createQuery(query
//		        .select(fromConversation)
//		        .where(conditions.toArray(new Predicate[] {}))
////		        .orderBy(builder.desc(fromConversation.get("created")))
//		        .orderBy(builder.desc(fromConversation.get(Conversation_.created)))
//		        .distinct(true)
//		);
//		
//		List<Conversation>conversations = typedQuery.getResultList();
//		
//		if(! conversations.isEmpty()){
//			for(Conversation foundConversation : conversations){
//				
//				Hibernate.initialize(foundConversation.getCorrespondents());
//				Set<Profile> conversationProfiles = new HashSet<Profile>();
//				for(Correspondent correspondent : foundConversation.getCorrespondents()){
//					Hibernate.initialize(correspondent.getOwningCorrespondentProfile());
//					conversationProfiles.add(correspondent.getOwningCorrespondentProfile());
//				}
//				
//				if(conversationProfiles.equals(profiles)){
//					Hibernate.initialize(foundConversation.getConversationMessages());
//					return foundConversation;
//				}
//			}
//		}
//		
//		return null;
//	}
//	
//	public Conversation getConversationByUUID(String uuid){
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Conversation> criteria = cb.createQuery(Conversation.class);
//		Root<Conversation> conversationRoot = criteria.from(Conversation.class);
//		
//		criteria.select(conversationRoot);
//		Predicate condition = cb.equal(conversationRoot.get(Conversation_.uuid), uuid);
//		criteria.where(condition);
//		TypedQuery<Conversation> query = em.createQuery(criteria);
//		
//		List<Conversation> conversations = query.getResultList();
//		
//		Conversation conversation = null;
//		if(! conversations.isEmpty()){
//			conversation = conversations.get(0);
//			
//			Hibernate.initialize(conversation.getCorrespondents());
//			Hibernate.initialize(conversation.getConversationMessages());
//			
//			for(Correspondent correspondent : conversation.getCorrespondents()){
//				Hibernate.initialize(correspondent.getOwningCorrespondentProfile());
//				Hibernate.initialize(correspondent.getOwningCorrespondentProfile().getProfilePicture());
//			}
//			
//			for(Message message : conversation.getConversationMessages()){
//				Hibernate.initialize(message.getProfile());
//			}
//		}
//		
//		return conversation;
//	}
}
