package com.elevysi.site.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "comments")
@FilterDefs({
	@FilterDef(name="commentItemIs", 
			defaultCondition="item_type='posts'",
			parameters={
			@org.hibernate.annotations.ParamDef(name="item_typeValue", type = "java.lang.String")
	}),
	@FilterDef(name="postComment", defaultCondition="item_type = 'posts'"),
	@FilterDef(name="playComment", defaultCondition="item_type = plays")
	
})
//@org.hibernate.annotations.Filter(name="commentItemIs", condition="item_type=:item_typeValue")
@org.hibernate.annotations.Filter(name="postComment")
public class Comment extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2077971011746424990L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="item_id")
	private Integer itemId;
		
	//Original comment one can potentially repy to
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="reply_to")
	private Comment originalComment;
	
	@OneToMany(mappedBy = "originalComment", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Comment> replies = new HashSet<Comment>();
	

	public Comment getOriginalComment() {
		return originalComment;
	}

	public void setOriginalComment(Comment originalComment) {
		this.originalComment = originalComment;
	}

	public Set<Comment> getReplies() {
		return replies;
	}

	public void setReplies(Set<Comment> replies) {
		this.replies = replies;
	}



	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@Column(name="item_type")
	@org.hibernate.annotations.Filter(name="postComment")
	private String itemType;
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}



	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="item_id", nullable = false, insertable = false, updatable = false)
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="item_id", nullable = false, insertable = false, updatable = false)
	private Play play;
	
	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="profile_id")
	private Profile profile;
	
	@Column(nullable = false)
	@Size(min=1, message ="Comment cannot be left empty")
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	private String anonymous_email;
	
	
	private String anonymous_name;
	
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	public String getAnonymous_email() {
		return anonymous_email;
	}

	public void setAnonymous_email(String anonymous_email) {
		this.anonymous_email = anonymous_email;
	}

	public String getAnonymous_name() {
		return anonymous_name;
	}

	public void setAnonymous_name(String anonymous_name) {
		this.anonymous_name = anonymous_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}	

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Comment))
            return false;
 
        final Comment comment  = (Comment)object;
 
        if (id != null && comment.getId() != null) {
            return id.equals(comment.getId());
        }
        return false;
    }
	
	

}
