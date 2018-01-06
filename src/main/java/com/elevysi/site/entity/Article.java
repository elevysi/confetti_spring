package com.elevysi.site.entity;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="articles")
public class Article extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8473276315327169869L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@ManyToOne
	@JoinColumn(name="seller", referencedColumnName="id")
	private Profile seller;
	
	@ManyToOne
	@JoinColumn(name="buyer", referencedColumnName="id")
	private Profile buyer;
	
	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "owningAvatar", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Upload> articleAvatar = new HashSet<Upload>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Profile getSeller() {
		return seller;
	}

	public void setSeller(Profile seller) {
		this.seller = seller;
	}
	
	public Profile getBuyer() {
		return buyer;
	}

	public void setBuyer(Profile buyer) {
		this.buyer = buyer;
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
	
	public Set<Upload> getArticleAvatar() {
		return articleAvatar;
	}


	public void setArticleAvatar(Set<Upload> articleAvatar) {
		this.articleAvatar = articleAvatar;
	}
}
