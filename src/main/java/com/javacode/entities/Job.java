package com.javacode.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Setter
@Getter
@NoArgsConstructor
public class Job {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "jobTitle")
	private String jobTitle;

	@Column(name = "language")
	private String language;

	@Column(name = "description")
	private String description;
	
	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "salary")
	private Float salary;

	@Column(name = "urlDetailImage")
	private String urlDetailImage;

	@OneToMany(mappedBy = "job")
	private List<RegJob> regJobs;

	@OneToMany(mappedBy = "job") // giong cmt
	private List<RatingJob> ratingJobs;

	@OneToMany(mappedBy = "job")
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "company_id", insertable = false, updatable = false)
	private Company company;

}