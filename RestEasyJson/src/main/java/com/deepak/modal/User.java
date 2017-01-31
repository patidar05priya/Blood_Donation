package com.deepak.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	
	@NamedQuery(name = "isUserIdAvailable", query = "select u from User u where u.emailId=:email"),
	@NamedQuery(name = "changeUserPassword", query = "update User u set u.password = (:newpassword) where u.emailId = (:email) and u.password = (:oldpassword)")
})
@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	@Id
	@Column(name = "USERID_PK")
	@GeneratedValue
	private Integer id;
	@Basic
	private String username;
	@Enumerated(EnumType.STRING)
	public BLOODGROUP bloodgroup;
	@Basic
	private String address;
	@Basic
	@Column(length = 20)
	private String phonenumber;
	@Basic
	private Float age;
	@Basic
	private Float weight;
	@Basic
	private String areaCode;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;
	@Basic
	private Integer donationcount;
	/*@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastdonation;*/
	@Basic
	private Boolean isavailable;
	@Enumerated(EnumType.STRING)
	public USERTYPE usertype;
	@Basic
	private String emailId;
	@Basic
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public enum BLOODGROUP {
		AB_POSITIVE,AB_NEGATIVE,A_POSITIVE,A_NEGATIVE,B_POSITIVE,B_NEGATIVE,O_POSITIVE,O_NEGATIVE;
	}
	public enum USERTYPE {
		DONNER, RECEIVER;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Float getAge() {
		return age;
	}
	public void setAge(Float age) {
		this.age = age;
	}
	public float getWeight() {
		return weight;
	}

	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getDonationcount() {
		return donationcount;
	}
	/*public Date getLastdonation() {
		return lastdonation;
	}
	public void setLastdonation(Date lastdonation) {
		this.lastdonation = lastdonation;
	}*/

	public USERTYPE getUsertype() {
		return usertype;
	}
	public void setUsertype(USERTYPE usertype) {
		this.usertype = usertype;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public Boolean getIsavailable() {
		return isavailable;
	}
	public void setIsavailable(Boolean isavailable) {
		this.isavailable = isavailable;
	}
	
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public void setDonationcount(Integer donationcount) {
		this.donationcount = donationcount;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", bloodgroup="
				+ bloodgroup + ", address=" + address + ", phonenumber="
				+ phonenumber + ", age=" + age + ", weight=" + weight
				+ ", areaCode=" + areaCode + ", createdon=" + createdon
				+ ", donationcount=" + donationcount + ", isavailable="
				+ isavailable + ", usertype=" + usertype + ", emailId="
				+ emailId + ", password=" + password + "]";
	}
	public User(Integer id, String username, BLOODGROUP bloodgroup,
			String address, String phonenumber, Float age, Float weight,
			String areaCode, Date createdon, Integer donationcount,
			Boolean isavailable, USERTYPE usertype, String emailId,
			String password) {
		super();
		this.id = id;
		this.username = username;
		this.bloodgroup = bloodgroup;
		this.address = address;
		this.phonenumber = phonenumber;
		this.age = age;
		this.weight = weight;
		this.areaCode = areaCode;
		this.createdon = createdon;
		this.donationcount = donationcount;
		this.isavailable = isavailable;
		this.usertype = usertype;
		this.emailId = emailId;
		this.password = password;
	}
}
