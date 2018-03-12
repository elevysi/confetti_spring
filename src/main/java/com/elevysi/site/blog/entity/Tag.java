package com.elevysi.site.blog.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;

@Entity
@Table(name ="tags")
@FilterDef(name="postTag", defaultCondition="item_type = post")
public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1056328424543695387L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer item_id;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	private String item_type;
	
	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

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

}
