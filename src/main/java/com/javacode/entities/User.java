package com.javacode.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor

public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private int role;

	@OneToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private Profile profile;

	@OneToMany(mappedBy = "user") // we need to duplicate the physical information
	private List<Comment> comments;

	@OneToMany(mappedBy = "user") // we need to duplicate the physical information
	private List<RegJob> regjobs;

	@OneToMany(mappedBy = "user") // ok
	private List<RatingJob> ratingjobs;

}
