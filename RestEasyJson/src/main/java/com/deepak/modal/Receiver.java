package com.deepak.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RECEIVER")
public class Receiver implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RECEIVERID_PK")
	@GeneratedValue
	private int id;
	
	@Basic
	private String name;
	@Basic
	private int age;
	@Basic
	private String bloodgroup;
	@Basic
	private String address;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;
	@Basic
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Receiver [id=" + id + ", name=" + name + ", age=" + age
				+ ", bloodgroup=" + bloodgroup + ", address=" + address
				+ ", createdon=" + createdon + ", description=" + description
				+ "]";
	}
	
	
	
	
	

}
