package com.elevysi.site.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name="products")
public class Product {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer id;
//	private String title;
//	private String description;
//	private Double price;
//	
//	@Column(name="sale_price")
//	private Double salePrice;
//	
//	@ManyToOne
//	@JoinColumn(name="profile_id", referencedColumnName="id")
//	private Profile productProfile;
//	
//	
////	@OneToOne(cascade = CascadeType.ALL, mappedBy="boughtProfile", fetch = FetchType.LAZY)
////	@Column(name="bought_by")
////	private Profile buyingProfile;
//	
//	
//	@Column(name = "created", columnDefinition="DATETIME")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date created;
//	
//	@Column(name = "modified", columnDefinition="DATETIME")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date modified;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//
//	public Double getSalePrice() {
//		return salePrice;
//	}
//
//	public void setSalePrice(Double salePrice) {
//		this.salePrice = salePrice;
//	}
//
//	public Profile getProductProfile() {
//		return productProfile;
//	}
//
//	public void setProductProfile(Profile productProfile) {
//		this.productProfile = productProfile;
//	}
//
//	public Date getCreated() {
//		return created;
//	}
//
//	public void setCreated(Date created) {
//		this.created = created;
//	}
//
//	public Date getModified() {
//		return modified;
//	}
//
//	public void setModified(Date modified) {
//		this.modified = modified;
//	}
//	


}
