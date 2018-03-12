package com.elevysi.site.blog.soa.client.entity.dto;

public class OauthGrantDTO {
	
	private String grant_type;
	private String scope;
	private String username;
	private String password;
	
	public OauthGrantDTO(String grant_type, String scope, String username, String password) {
		super();
		this.grant_type = grant_type;
		this.scope = scope;
		this.username = username;
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
