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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "jobTittle")
	private String jobTittle;

	@Column(name = "language")
	private String language;

	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "salary")
	private Float salary;

	@Column(name = "urlDetailImage")
	private String urlDetailImage;

	@ManyToOne
	@JoinColumn(name = "job_id", insertable = false, updatable = false)
	private RegJob regjob;
	
	@OneToOne(mappedBy = "id") // giong cmt
	private RatingJob ratingjob;
	
	@OneToOne(mappedBy = "id")
	private Comment comment;

	@ManyToOne
	@JoinColumn(name = "company_id", insertable = false, updatable = false)
	private Company company;

	public Job() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTittle() {
		return jobTittle;
	}

	public void setJobTittle(String jobTittle) {
		this.jobTittle = jobTittle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getUrlDetailImage() {
		return urlDetailImage;
	}

	public void setUrlDetailImage(String urlDetailImage) {
		this.urlDetailImage = urlDetailImage;
	}

	public RegJob getRegjob() {
		return regjob;
	}

	public void setRegjob(RegJob regjob) {
		this.regjob = regjob;
	}

	public RatingJob getRatingjob() {
		return ratingjob;
	}

	public void setRatingjob(RatingJob ratingjob) {
		this.ratingjob = ratingjob;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Job(int id, String jobTittle, String language, Date created_at, Float salary, String urlDetailImage,
			RegJob regjob, RatingJob ratingjob, Company company) {
		this.id = id;
		this.jobTittle = jobTittle;
		this.language = language;
		this.created_at = created_at;
		this.salary = salary;
		this.urlDetailImage = urlDetailImage;
		this.regjob = regjob;
		this.ratingjob = ratingjob;
		this.company = company;
	}

	// getter - setter
}
