package com.javacode.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "gender")
	private int gender;

	@Column(name = "DOB")
	private Date DOB;

	@Column(name = "urlAvatar")
	private String urlAvatar;

	@Column(name = "school")
	private String school;

	@Column(name = "certificate")
	private String certificate;

	@OneToOne(mappedBy = "id")
	private User user;

	public Profile() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile(int id, int gender, Date dOB, String urlAvatar, String school, String certificate, User user) {
		this.id = id;
		this.gender = gender;
		DOB = dOB;
		this.urlAvatar = urlAvatar;
		this.school = school;
		this.certificate = certificate;
		this.user = user;
	}

	// getter - setter
}
