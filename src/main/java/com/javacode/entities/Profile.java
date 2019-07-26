package com.javacode.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Setter
@Getter
@NoArgsConstructor

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

	@OneToOne(mappedBy = "profile")
	private User user;
}
