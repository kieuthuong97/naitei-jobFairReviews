package com.javacode.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "reg_jobs")
public class RegJob {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "status")
	private int status;

	@ManyToOne 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "reg_job") // we need to duplicate the physical information
	private List<Job> jobs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public RegJob(int id, int status, User user, List<Job> jobs) {
		super();
		this.id = id;
		this.status = status;
		this.user = user;
		this.jobs = jobs;
	}

}
