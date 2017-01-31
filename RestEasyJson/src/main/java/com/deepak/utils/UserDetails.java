package com.deepak.utils;

import java.io.Serializable;

import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;

public class UserDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private Long activerequest;
	
	private Integer userid;
	
	private USERTYPE usertype;
	
	private BLOODGROUP bloodgroup;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public USERTYPE getUsertype() {
		return usertype;
	}

	public void setUsertype(USERTYPE usertype) {
		this.usertype = usertype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getActiverequest() {
		return activerequest;
	}

	public void setActiverequest(Long activerequest) {
		this.activerequest = activerequest;
	}

	
	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", activerequest="
				+ activerequest + ", userid=" + userid + ", usertype="
				+ usertype + ", bloodgroup=" + bloodgroup + "]";
	}

	public UserDetails(String username, Long activerequest, Integer userid,
			USERTYPE usertype, BLOODGROUP bloodgroup) {
		super();
		this.username = username;
		this.activerequest = activerequest;
		this.userid = userid;
		this.usertype = usertype;
		this.bloodgroup = bloodgroup;
	}

	public UserDetails(){}
	
}
