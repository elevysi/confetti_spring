package com.elevysi.site.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="correspondents")
public class Correspondent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="conversation_id", referencedColumnName="id")
	private Conversation conversation;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_id", referencedColumnName="id")
	private Profile owningCorrespondentProfile;
	
	@Column(name="created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name="modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	public void setConversation(Conversation conversation, boolean add){
		this.conversation = conversation;
		if(conversation != null && add){
			conversation.addCorrespondent(this, false);
		}
	}

	public Profile getOwningCorrespondentProfile() {
		return owningCorrespondentProfile;
	}

	public void setOwningCorrespondentProfile(Profile owningCorrespondentProfile) {
		this.owningCorrespondentProfile = owningCorrespondentProfile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public boolean equals(Object object){
		
		if(object == this) return true;
		if(object == null || !(object instanceof Correspondent)) return false;
		final Correspondent correspondent = (Correspondent)object;
		if(id !=null && correspondent.getId() != null) return id.equals(correspondent.getId());
		
		return false;
	}

}
