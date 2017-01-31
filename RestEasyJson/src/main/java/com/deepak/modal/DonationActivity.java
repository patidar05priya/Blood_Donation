package com.deepak.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.deepak.modal.User.BLOODGROUP;

@NamedQueries({
	
	@NamedQuery(name = "findExistingRequest", query = "select da from DonationActivity da where da.username = (:userName) and da.bloodgroup = (:bloodgroup) and da.status = (:status)"),
	@NamedQuery(name = "findActiveRequest", query = "select da from DonationActivity da where da.bloodgroup = (:bloodgroup) and da.status = (:status)"),
	@NamedQuery(name = "findSubmittedRequest", query = "select da from DonationActivity da where da.userid = (:userid) and da.status in (:status)")
})

@Entity
@Table(name = "DONATIONACTIVITY")
public class DonationActivity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DONATIONID_PK")
	@GeneratedValue
	private int id;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID_FK", nullable = true)
	private User user;*/
	//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID_FK", nullable = true)
	@Column(name = "USERID_FK")
	private Integer userid;
	
	@Basic
	private String username;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationtime;
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVERID_FK", nullable = true)
	private Receiver receiverid;*/
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date donationdate;
	
	@Basic
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Status{
		PENDING, DONE
	}
	@Basic
	private Long phonenumber;
	
	@Basic
	private String description;

	
	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Enumerated(EnumType.STRING)
	private BLOODGROUP bloodgroup;

	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	/*public Receiver getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(Receiver receiverid) {
		this.receiverid = receiverid;
	}
*/
	public Date getDonationdate() {
		return donationdate;
	}

	public void setDonationdate(Date donationdate) {
		this.donationdate = donationdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "DonationActivity [id=" + id + ", userid=" + userid
				+ ", username=" + username + ", creationtime=" + creationtime
				+ ", donationdate=" + donationdate + ", address=" + address
				+ ", status=" + status + ", phonenumber=" + phonenumber
				+ ", description=" + description + ", bloodgroup=" + bloodgroup
				+ "]";
	}
	
}
